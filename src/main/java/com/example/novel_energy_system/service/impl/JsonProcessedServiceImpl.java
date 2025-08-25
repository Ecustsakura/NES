package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.service.JsonProcessedService;
import com.example.novel_energy_system.service.PictureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

@Service
public class JsonProcessedServiceImpl implements JsonProcessedService {

    @Autowired
    private PictureService pictureService;

    public static class NodeDetail {
        public String nodeID;
        public String nodeName;
        public String groupName;
        public String typeName;
        public Map<String, Object> others = new HashMap<>();
    }

    @Override
    public Map<String, Object> getLayerMapFromDB(int id) throws JsonProcessingException {
        String jsonStr = pictureService.selectJsonDataById(id);

        // 空值判断：如果JSON字符串为空，直接返回空Map
        if (jsonStr == null || jsonStr.trim().isEmpty()) {
            System.out.println("获取的JSON字符串为空");
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonStr);

        // 提取所有层
        JsonNode layerMap = root.at("/json/layerMap");

        // 根层为 main
        Map<String, Object> mainLayer = buildLayer("main", layerMap.get("main"), layerMap, mapper, "None");

        // 封装为主结构
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("main", mainLayer);

        // 输出
        return result;
    }

    /*
     * 递归构造某一层
     */
    private static Map<String, Object> buildLayer(String layerName, JsonNode thisLayerNode, JsonNode allLayers, ObjectMapper mapper, String parentLayer) {
        Map<String, Object> thisLayer = new LinkedHashMap<>();

        // devices
        JsonNode nodeMap = thisLayerNode.get("nodeMap");
        Map<String, Map<String, NodeDetail>> typeDeviceMap = new HashMap<>();
        Map<String, Integer> typeCounters = new HashMap<>();

        if (nodeMap != null && nodeMap.size() > 0) {
            for (Iterator<JsonNode> it = nodeMap.elements(); it.hasNext(); ) {
                JsonNode node = it.next();
                String typeName = node.get("typeName").asText();
                int idx = typeCounters.getOrDefault(typeName, 0) + 1;
                typeCounters.put(typeName, idx);

                NodeDetail detail = new NodeDetail();
                detail.nodeID = node.get("nodeID").asText();
                detail.nodeName = node.get("nodeName").asText();
                detail.groupName = node.get("groupName").asText();
                detail.typeName = typeName;
                node.fieldNames().forEachRemaining(f -> {
                    if (!Arrays.asList("nodeID", "nodeName", "groupName", "typeName").contains(f)) {
                        detail.others.put(f, node.get(f));
                    }
                });
                typeDeviceMap.computeIfAbsent(typeName, k -> new LinkedHashMap<>())
                        .put(String.valueOf(idx), detail);
            }
        }

        // 将设备信息添加到当前层
        thisLayer.put("devices", typeDeviceMap);

        // 递归处理下一层(子层)
        String thisLayerID = thisLayerNode.has("layerID") ? thisLayerNode.get("layerID").asText() : layerName;
        String childLayerName = null;

        // 主层的子层，一般是该层下唯一非"main"的Key. parentID为本层
        for (Iterator<String> it = allLayers.fieldNames(); it.hasNext(); ) {
            String nextLayerKey = it.next();
            if (nextLayerKey.equals(layerName)) continue;
            JsonNode nextLayerNode = allLayers.get(nextLayerKey);
            if (nextLayerNode.has("parentID")
                    && nextLayerNode.get("parentID").asText().equals(thisLayerID)) {
                // 找到了子层, 名称为Key
                childLayerName = nextLayerKey;
                Map<String, Object> childLayer = buildLayer(childLayerName, nextLayerNode, allLayers, mapper, layerName);
                thisLayer.put(childLayerName, childLayer);
                break; // 只处理一个子层, 如有多个可拆成list递归
            }
        }

        // 其它固定字段
        thisLayer.put("connection", new LinkedHashMap<>());
        thisLayer.put("parent", parentLayer == null ? "None" : parentLayer);
        thisLayer.put("child", childLayerName == null ? "None" : childLayerName);

        return thisLayer;
    }
}