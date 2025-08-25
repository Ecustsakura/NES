package com.example.novel_energy_system.controller;

import com.example.novel_energy_system.service.impl.PowerGridServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.Executor;

/**
 * 用于实现前后端数据实时通讯的通信技术Sse
 * @eo.api-type  http
 * @eo.groupName Sse
 * @eo.path
 */

@RestController
public class SseController {

    // 注入自定义的线程池，通过@Qualifier指定bean名称
    @Autowired
    @Qualifier("taskExecutor")
    private Executor taskExecutor;

    @Autowired
    private PowerGridServiceImpl powerGridService;

    /**
     * 调用接口请求时，每秒向客户端发送一组powerGrid中的PFjson。
     * @return 返回json结果
     */

    @GetMapping("/sse")
    public SseEmitter handleSse() {
        // 设置超时时间，0表示永不超时（根据实际需求调整）
        SseEmitter emitter = new SseEmitter(0L);

        // 使用自定义线程池执行任务
        taskExecutor.execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    // 发送SSE消息，可指定事件类型（可选）
                    emitter.send(SseEmitter.event()
                            .name("message")  // 事件名称，前端可根据名称监听
                            .data(powerGridService.getPFJson()));  // 消息内容
                    Thread.sleep(1000);  // 每秒发送一次
                }
                // 发送完成后关闭连接
                emitter.complete();
            } catch (IOException e) {
                // 发送失败时关闭连接并通知错误
                emitter.completeWithError(e);
            } catch (InterruptedException e) {
                // 处理中断异常
                Thread.currentThread().interrupt(); // 恢复中断状态
                emitter.completeWithError(e);
            }
        });

        // 连接关闭时的回调
        emitter.onCompletion(() -> {
            System.out.println("SSE连接已完成");
        });

        emitter.onError((e) -> {
            System.out.println("SSE连接发生错误: " + e.getMessage());
        });

        emitter.onTimeout(() -> {
            System.out.println("SSE连接超时");
        });

        return emitter;
    }
}
