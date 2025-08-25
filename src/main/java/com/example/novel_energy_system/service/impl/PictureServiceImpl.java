package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.mapper.DrawMapper;
import com.example.novel_energy_system.pojo.Picture;
import com.example.novel_energy_system.service.PictureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private DrawMapper drawMapper;

    @Override
    public int addPicture(Picture picture) {
        return drawMapper.insertPicture(picture);
    }

    @Override
    public int updatePicture(Picture picture) {
        return drawMapper.updatePicture(picture);
    }

    @Override
    public int deletePicture(int id) {
        return drawMapper.deletePicture(id);
    }

    @Override
    public PageInfo<Picture> selectPicture(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictureList = drawMapper.selectPicture();
        return new PageInfo<>(pictureList);
    }

    @Override
    public PageInfo<Picture> selectPictureByContent(String content, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Picture> pictureList = drawMapper.selectPictureByContent(content);
        return new PageInfo<>(pictureList);
    }

    @Override
    public int updatePictureByjson(Picture picture) {
        return drawMapper.updatePictureByJson(picture);
    }

    @Override
    public String selectJsonDataById(Integer id) {
        return drawMapper.selectJsonDataById(id);
    }

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public String getSchema() throws JsonProcessingException {
        // 创建 ALK 的子字段
        Map<String, Object> resistance = new HashMap<>();
        resistance.put("type", "string");
        resistance.put("unit", new String[]{"ohm", "kohm"});

        Map<String, Object> impedance = new HashMap<>();
        impedance.put("type", "string");
        impedance.put("unit", new String[]{"ohm", "kohm"});

        // 创建 ALK 字段
        Map<String, Object> ELecline = new HashMap<>();
        ELecline.put("resistance", resistance);
        ELecline.put("impedance", impedance);

        return objectMapper.writeValueAsString(ELecline);
    }


}