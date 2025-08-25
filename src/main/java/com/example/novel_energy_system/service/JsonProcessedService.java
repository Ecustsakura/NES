package com.example.novel_energy_system.service;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface JsonProcessedService {
    Map<String, Object> getLayerMapFromDB(int id) throws JsonProcessingException;
}
