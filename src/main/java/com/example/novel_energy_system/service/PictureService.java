package com.example.novel_energy_system.service;

import com.example.novel_energy_system.pojo.Picture;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PictureService {
    int addPicture(Picture picture);
    int updatePicture(Picture picture);
    int deletePicture(int id);
    PageInfo<Picture> selectPicture(int pageNum, int pageSize);
    PageInfo<Picture> selectPictureByContent(String content,int pageNum, int pageSize);
}
