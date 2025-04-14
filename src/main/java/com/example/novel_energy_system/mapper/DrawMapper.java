package com.example.novel_energy_system.mapper;

import com.example.novel_energy_system.pojo.Picture;

import java.util.List;

public interface DrawMapper {

    int insertPicture(Picture picture);
    int deletePicture(int id);
    int updatePicture(Picture picture);
    List<Picture> selectPicture();
}
