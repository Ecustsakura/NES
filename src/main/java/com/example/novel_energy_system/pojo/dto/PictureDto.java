package com.example.novel_energy_system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto implements Serializable {


    private String pictureName;
    private String description;
    private static final long serialVersionUID = 1L;

}
