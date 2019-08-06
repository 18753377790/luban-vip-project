package com.luban.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * Redis存储
 * @Date 2019/7/27 11:22
 * @Version 1.0
 */
@Component
public class RedisStore implements StoreInterface{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
    }
}
