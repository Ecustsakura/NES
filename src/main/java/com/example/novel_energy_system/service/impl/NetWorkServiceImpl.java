package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.mapper.PowerFlowMapper;
import com.example.novel_energy_system.pojo.PowerFlow;
import com.example.novel_energy_system.service.NetWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Service
public class NetWorkServiceImpl implements NetWorkService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PowerFlowMapper powerFlowMapper;

    public String pf_calculate() {
        String url = "http://localhost:5000/network/powerflow";

        try {
            // 发送请求获取潮流计算结果
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                return "Flask 服务器返回错误: " + response.getStatusCode();
            }

            // 解析 Flask 返回的数据
            Map<String, Map<String, Double>> voltageResults = (Map<String, Map<String, Double>>) response.getBody().get("voltage_results");
            Map<String, Map<String, Double>> angleResults = (Map<String, Map<String, Double>>) response.getBody().get("angle_results");
            Map<String, Map<String, Double>> pResults = (Map<String, Map<String, Double>>) response.getBody().get("p_result");
            Map<String, Map<String, Double>> qResults = (Map<String, Map<String, Double>>) response.getBody().get("q_result");

            // 处理数据并存入数据库
            savePowerFlowData(voltageResults, angleResults, pResults, qResults);

            return "潮流计算结果已存入数据库";

        } catch (Exception e) {
            return "请求 Flask 服务出错: " + e.getMessage();
        }
    }

    private void savePowerFlowData(Map<String, Map<String, Double>> voltageResults,
                                   Map<String, Map<String, Double>> angleResults,
                                   Map<String, Map<String, Double>> pResults,
                                   Map<String, Map<String, Double>> qResults) {

        for (String key : voltageResults.keySet()) {
            String[] parts = key.split(","); // "1,0" -> [1, 0]
            int node = Integer.parseInt(parts[0]);
            float time = Float.parseFloat(parts[1]);

            double voltage = voltageResults.get(key).get("value");
            double angle = angleResults.get(key).get("value");
            double p = pResults.get(key).get("value");
            double q = qResults.get(key).get("value");

            // 创建 PowerFlow 对象并存入数据库
            PowerFlow powerFlow = new PowerFlow();
            powerFlow.setNode(node);
            powerFlow.setTime(time);
            powerFlow.setVoltage((float) voltage);
            powerFlow.setAngle((float) angle);
            powerFlow.setP((float) p);
            powerFlow.setQ((float) q);

            powerFlowMapper.insert(powerFlow);
        }
    }
}
//    public String pf_calculate() {
//
//        String url = "http://localhost:5000/network/powerflow";
//        try {
//            // 发送 `GET` 请求到 Flask 服务器
//            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
//
//            // 处理响应
//            if (response.getStatusCode().is2xxSuccessful()) {
//                System.out.println("潮流计算结果: " + response.getBody());
//                return response.getBody();
//            } else {
//                System.err.println("潮流计算请求失败: " + response.getStatusCode());
//                return "潮流计算失败: " + response.getStatusCode();
//            }
//        } catch (Exception e) {
//            System.err.println("请求 Flask 服务出错: " + e.getMessage());
//            return "无法连接到潮流计算服务";
//        }
//    }
//}
