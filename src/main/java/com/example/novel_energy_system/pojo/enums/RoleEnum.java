package com.example.novel_energy_system.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum RoleEnum {
    ADMIN(1, "管理员"),
    USER(2, "普通用户"),
    GUEST(3, "访客");

    @EnumValue // MyBatis-Plus 存储数据库时使用的值
    private final int code;
    private final String description;

    RoleEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
