package com.example.novel_energy_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.example.novel_energy_system.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class NovelEnergySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelEnergySystemApplication.class, args);
    }

}
