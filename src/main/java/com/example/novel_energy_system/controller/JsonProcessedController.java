package com.example.novel_energy_system.controller;

import com.example.novel_energy_system.service.JsonProcessedService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



/**
 * json数据转化控制器类，用于处理与json数据相关的HTTP请求。
 * 提供获取json的接口。
 * @eo.api-type  http
 * @eo.groupName JsonProcessed
 * @eo.path
 */

@RestController
@RequestMapping("json")
public class JsonProcessedController {

    @Autowired
    private JsonProcessedService jsonProcessedService;

    /**

     * 根据传入的图片id信息，调用服务层方法获取该图片对应的配置json数据。
     * @param id 图片信息中id的部分，{id}
     * @return 返回添加结果，成功时返回包含成功信息的Result对象，失败时返回包含错误信息的Result对象。
     */

    @GetMapping("/jsonProcess/{id}")
    public Map<String,Object> processJsonDataById(@PathVariable("id") Integer id) throws JsonProcessingException {
        return jsonProcessedService.getLayerMapFromDB(id);
    }
}
