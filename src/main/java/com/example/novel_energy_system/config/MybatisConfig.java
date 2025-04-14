package com.example.novel_energy_system.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties prop = new Properties();
        prop.setProperty("offsetAsPageNum", "true");
        prop.setProperty("rowBoundsWithCount", "true");
        prop.setProperty("reasonable", "true");
        prop.setProperty("dialect", "Mysql");
        pageHelper.setProperties(prop);
        return pageHelper;
    }
}
