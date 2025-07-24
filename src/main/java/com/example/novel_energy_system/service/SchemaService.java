package com.example.novel_energy_system.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface SchemaService {

    String getElecline() throws JsonProcessingException;
    String getALK() throws JsonProcessingException;
    String getPEM() throws JsonProcessingException;
    String getPEMFC() throws JsonProcessingException;
    String getWIND() throws JsonProcessingException;
    String getPV() throws JsonProcessingException;
    String getSolarTB() throws JsonProcessingException;
    String getMolten_salt_steam_generator() throws JsonProcessingException;
    String getMolten_salt_heat_storage() throws JsonProcessingException;
    String getLava_heat_storage() throws JsonProcessingException;
    String getSteam_turbine() throws JsonProcessingException;
    String getRefinery() throws JsonProcessingException;
    String geth2_gas() throws JsonProcessingException;
}
