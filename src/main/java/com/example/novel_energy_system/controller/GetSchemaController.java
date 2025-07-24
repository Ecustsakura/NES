package com.example.novel_energy_system.controller;


import com.example.novel_energy_system.service.SchemaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schema")
public class GetSchemaController {

    @Autowired
    private SchemaService schemaService;


    /**
     * 返回单位及格式的接口
     * 根据传入的type，调用服务层方法更新对应type的json。
     * @param type
     * @return 返回json结果
     */
    @GetMapping("/module")
    public String getschema(@RequestParam(value = "type") String type) throws JsonProcessingException {
        if (type == null || type.isEmpty()) {
            return ("Picture ID must be provided");
        } else if ("Elecline".equals(type)) {
            return schemaService.getElecline();
        } else if ("ALK".equals(type)) {
            return schemaService.getALK();
        } else if ("PEM".equals(type)) {
            return schemaService.getPEM();
        } else if ("PEMFC".equals(type)) {
            return schemaService.getPEMFC();
        } else if ("WIND".equals(type)) {
            return schemaService.getWIND();
        } else if ("PV".equals(type)) {
            return schemaService.getPV();
        } else if ("SolarTB".equals(type)) {
            return schemaService.getSolarTB();
        } else if ("Molten_salt_steam_generator".equals(type)) {
            return schemaService.getMolten_salt_steam_generator();
        } else if ("Molten_salt_heat_storage".equals(type)) {
            return schemaService.getMolten_salt_heat_storage();
        } else if ("Lava_heat_storage".equals(type)) {
            return schemaService.getLava_heat_storage();
        } else if ("Steam_turbine".equals(type)) {
            return schemaService.getSteam_turbine();
        } else if ("Refinery".equals(type)) {
            return schemaService.getRefinery();
        } else if ("h2_gas".equals(type)) {
            return schemaService.geth2_gas();
        }
        return schemaService.getElecline();
    }
}
