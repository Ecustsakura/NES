package com.example.novel_energy_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("picture")
public class Picture implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "name")
    private String name;

    @TableField(value = "createtime")
    private LocalDateTime createTime;

    @TableField(value = "updatetime")
    private LocalDateTime updateTime;

    @TableField(value = "description")
    private String description;

    private static final long serialVersionUID = 1L;
}
