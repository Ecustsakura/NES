package com.example.novel_energy_system.interceptor;

import com.example.novel_energy_system.annotation.Cacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheableAspect {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Pointcut("@annotation(com.example.novel_energy_system.annotation.Cacheable)")
    public void cacheablePointcut(){ }

    @Around("cacheablePointcut() && @annotation(cacheable)")
    public Object cacheMethod(ProceedingJoinPoint joinPoint, Cacheable cacheable) throws Throwable {
        // 生成缓存键
        String key = generateCacheKey(joinPoint, cacheable.key());
        // 尝试从缓存中获取数据
        Object result = redisTemplate.opsForValue().get(key);
        if (result != null) {
            return result; // 缓存命中，直接返回缓存数据
        }

        // 缓存未命中，执行原方法
        result = joinPoint.proceed();
        // 将结果存入缓存
        redisTemplate.opsForValue().set(key, result, cacheable.ttl(), TimeUnit.SECONDS);
        return result;
    }
    private String generateCacheKey(ProceedingJoinPoint joinPoint, String key) {
        // 根据方法名和参数生成缓存键
        return joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + ":" + key;
    }
}

