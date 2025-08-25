package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.common.CodeMsg;
import com.example.novel_energy_system.common.Result;
import com.example.novel_energy_system.mapper.PowerFlowMapper;
import com.example.novel_energy_system.pojo.PowerFlow;
import com.example.novel_energy_system.service.PowerGridService;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.example.novel_energy_system.common.CodeMsg.SERVER_EXCEPTION;

@Service
public class PowerGridServiceImpl implements PowerGridService {

    private static final Logger logger = LoggerFactory.getLogger(NetWorkServiceImpl.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PowerFlowMapper powerFlowMapper;



    @Override
    public Map<String, Object> getPFJson() {
        String url = "http://localhost:5000/network/powerflow";


        try {
            // 发送请求获取潮流计算结果
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.error("Flask 服务器返回错误: {}", response.getStatusCode());
                return null;
            }

            return response.getBody();
        } catch (Exception e) {
            logger.error("请求 Flask 服务出错: {}", e.getMessage(), e);
            return null;
        }
    }

    // 明确泛型为Void，因为保存操作无需返回数据
    @Override
    public Result<Void> savePF() {
        String url = "http://localhost:5000/network/powerflow";
        try {
            // 1. 调用接口获取数据并转换为PowerFlow
            PowerFlow powerFlow = restTemplate.getForObject(url, PowerFlow.class);
            if (powerFlow == null) {
                return Result.error(new CodeMsg(500, "接口返回空数据"));
            }

            // 2. 插入数据库
            int ans = powerFlowMapper.insert(powerFlow);
            if (ans > 0) {
                return Result.success(); // 若需要泛型参数，可改为Result.success(null)
            } else {
                return Result.error(new CodeMsg(500, "数据库插入失败"));
            }

        } catch (HttpMessageNotReadableException e) {
            logger.error("JSON转换失败（PowerFlow映射错误）", e);
            return Result.error(new CodeMsg(500, "数据格式错误"));
        } catch (RestClientException e) {
            logger.error("调用Flask接口失败", e);
            return Result.error(new CodeMsg(500, "接口调用失败"));
        } catch (Exception e) {
            logger.error("保存数据时发生未知错误", e);
            return Result.error(SERVER_EXCEPTION);
        }
    }


}
