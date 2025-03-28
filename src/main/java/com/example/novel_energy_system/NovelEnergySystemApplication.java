package com.example.novel_energy_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.novel_energy_system.mapper")
public class NovelEnergySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelEnergySystemApplication.class, args);
    }

}
