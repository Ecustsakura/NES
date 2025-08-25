package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.common.SseException;
import com.example.novel_energy_system.common.SseMessage;
import com.example.novel_energy_system.service.SseService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class SseServiceImpl implements SseService {
    /**
     * k:客户端id  v:SseEmitter
     */
    private static Map<String, SseEmitter> sseEmitterMap = new ConcurrentHashMap<>();

    @Override
    public SseEmitter connect(String clientId) {
        //6小时后断开
        SseEmitter sseEmitter = new SseEmitter(6 * 60 * 60 * 1000L);
        SseMessage sseMessage = new SseMessage();
//        if (sseEmitterMap.containsKey(clientId)) {
//            return SseExceptionHandle(sseMessage, sseEmitter, "该clientId已绑定指定的客户端！clientId:" + clientId);
//        }
//
//        if (sseEmitterMap.size() > 1000) {
//            return SseExceptionHandle(sseMessage, sseEmitter, "客户端连接过多，请稍后重试！");
//        }
        // 连接成功需要返回数据，否则会出现待处理状态
        try {
            sseMessage.setCode(HttpServletResponse.SC_OK);
            sseMessage.setData("连接成功！");
            sseEmitter.send(sseMessage, MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            log.error("sse连接发送数据时出现异常：", e);
            throw new SseException("sse连接发送数据时出现异常！");
        }

        // 连接断开
        sseEmitter.onCompletion(() -> {
            log.info("sse连接断开，clientId为：{}", clientId);
            sseEmitterMap.remove(clientId);
        });

        // 连接超时
        sseEmitter.onTimeout(() -> {
            log.info("sse连接已超时，clientId为：{}", clientId);
            sseEmitterMap.remove(clientId);
        });

        // 连接报错
        sseEmitter.onError((throwable) -> {
            log.info("sse连接异常:", throwable);
            sseEmitterMap.remove(clientId);
        });
        sseEmitterMap.put(clientId, sseEmitter);
        //模拟拿到登录用户信息
        return sseEmitter;

    }

    @Override
    public void activeSendMessage(SseMessage sseMessage) {

    }

    @Override
    public void sendMessage() {

    }
}
