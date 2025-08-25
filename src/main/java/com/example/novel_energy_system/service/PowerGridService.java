package com.example.novel_energy_system.service;

import com.example.novel_energy_system.common.Result;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

public interface PowerGridService {
    Map<String,Object> getPFJson();
    Result<Void> savePF();
}
