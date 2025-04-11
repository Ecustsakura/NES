package com.example.novel_energy_system;


import com.example.novel_energy_system.config.RedisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = RedisConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void testKeyTypeOperation() {
        ValueOperations operations = redisTemplate.opsForValue();
        operations.set("name", "hjb");
        System.out.println(operations.get("name"));

    }
}
