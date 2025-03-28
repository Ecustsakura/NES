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
@TableName("powerflow")
public class PowerFlow implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private int id;

    @TableField(value = "node")
    private int node;

    @TableField(value = "angle")
    private float angle;

    @TableField(value = "voltage")
    private float voltage;

    @TableField(value = "p")
    private float p;

    @TableField(value = "q")
    private float q;

    @TableField(value = "time")
    private float time;

    private static final long serialVersionUID = 1L;
}
