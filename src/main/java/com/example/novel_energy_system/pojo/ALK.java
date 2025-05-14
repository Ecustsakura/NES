package com.example.novel_energy_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("alk")
public class ALK implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "node")
    private int node;

    @TableField(value = "num")
    private int num;

    @TableField(value = "time")
    private int time;

    @TableField(value = "current")
    private double current;

    @TableField(value = "p")
    private double p;

    @TableField(value = "temperature")
    private double temperature;

    @TableField(value = "voltage")
    private double voltage;

    @TableField(value = "m")
    private double m;

    private static final long serialVersionUID = 1L;
}
