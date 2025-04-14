package com.example.novel_energy_system.service.impl;

import com.example.novel_energy_system.mapper.DrawMapper;
import com.example.novel_energy_system.pojo.Picture;
import com.example.novel_energy_system.service.PictureService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
}
