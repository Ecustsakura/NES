package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.mapper.ALKMapper;
import com.example.novel_energy_system.mapper.PowerFlowMapper;
import com.example.novel_energy_system.pojo.ALK;
import com.example.novel_energy_system.pojo.PowerFlow;
import com.example.novel_energy_system.service.NetWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NetWorkServiceImpl implements NetWorkService {

    private static final Logger logger = LoggerFactory.getLogger(NetWorkServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PowerFlowMapper powerFlowMapper;

    @Autowired
    private ALKMapper alkMapper;

    @Override
    public String pf_calculate() {
        String url = "http://localhost:5000/network/powerflow";

        try {
            // 发送请求获取潮流计算结果
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                logger.error("Flask 服务器返回错误: {}", response.getStatusCode());
                return "Flask 服务器返回错误: " + response.getStatusCode();
            }

            // 解析 Flask 返回的数据
            Map<String, Object> body = response.getBody();
            if (body == null) {
                logger.error("Flask 返回的数据为空");
                return "Flask 返回的数据为空";
            }

            Map<String, Map<String, Double>> voltageResults = (Map<String, Map<String, Double>>) body.get("voltage_results");
            Map<String, Map<String, Double>> angleResults = (Map<String, Map<String, Double>>) body.get("angle_results");
            Map<String, Map<String, Double>> pResults = (Map<String, Map<String, Double>>) body.get("p_result");
            Map<String, Map<String, Double>> qResults = (Map<String, Map<String, Double>>) body.get("q_result");

            // 验证数据是否为空
            if (voltageResults == null || angleResults == null || pResults == null || qResults == null) {
                logger.error("Flask 返回的数据中缺少必要的字段");
                return "Flask 返回的数据中缺少必要的字段";
            }

            // 处理数据并存入数据库
            savePowerFlowData(voltageResults, angleResults, pResults, qResults);

            return "潮流计算结果已存入数据库";

        } catch (Exception e) {
            logger.error("请求 Flask 服务出错: {}", e.getMessage(), e);
            return "请求 Flask 服务出错: " + e.getMessage();
        }
    }

    @Override
    public String network_alk_calculate() {
        String url = "http://localhost:5000/network/alkelz";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            Map<String, Object> body = response.getBody();
            if (body == null) {
                throw new RuntimeException("Failed to fetch data from Flask service");
            }

            List<ALK> alkList = processFlaskData(body);
            int result = alkMapper.insertBatch(alkList);

            if (result > 0) {
                logger.info("插入成功: 插入了 {} 条数据", alkList.size());
                return "潮流计算结果已存入数据库";
            } else {
                logger.warn("插入失败: 未插入任何数据");
                return "潮流计算结果未存入数据库";
            }
        } catch (Exception e) {
            logger.error("请求 Flask 服务出错: {}", e.getMessage(), e);
            return "请求 Flask 服务出错: " + e.getMessage();
        }
    }
    private List<ALK> processFlaskData(Map<String, Object> body) {
        List<Double> V = (List<Double>) body.get("V");
        List<Double> I = (List<Double>) body.get("I");
        List<Double> T = (List<Double>) body.get("T");
        List<Double> P = (List<Double>) body.get("P");
        List<Double> m = (List<Double>) body.get("m");

        int size = V.size();
        List<ALK> alkList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ALK alk = new ALK();
            alk.setNode(1); // 假设 node 为 1，根据实际情况设置
            alk.setNum(i + 1); // 假设 num 为序号，从 1 开始
            alk.setTime(i); // 假设 time 为序号，根据实际情况设置
            alk.setCurrent(V.get(i));
            alk.setP(P.get(i));
            alk.setTemperature(T.get(i));
            alk.setVoltage(I.get(i));
            alk.setM(m.get(i));
            alkList.add(alk);
        }
        return alkList;
    }



    private void savePowerFlowData(Map<String, Map<String, Double>> voltageResults,
                                   Map<String, Map<String, Double>> angleResults,
                                   Map<String, Map<String, Double>> pResults,
                                   Map<String, Map<String, Double>> qResults) {

        for (String key : voltageResults.keySet()) {
            try {
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

                int result = powerFlowMapper.insertPowerFlow(powerFlow);
                if (result > 0) {
                    logger.info("插入成功: {}", powerFlow);
                } else {
                    logger.warn("插入失败: {}", powerFlow);
                }
            } catch (Exception e) {
                logger.error("处理数据时出错: {}", e.getMessage(), e);
            }
        }
    }
}