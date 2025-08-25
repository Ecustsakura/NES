package com.example.novel_energy_system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PowerFlowDto {
    private int node;
    private float angle;
    private float voltage;
    private float p;
    private float q;
    private float time;
    private static final long serialVersionUID = 1L;
}
