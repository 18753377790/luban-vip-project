package com.modules.admin.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * Redis工具类
 * @Date 2019/7/30 12:05
 * @Version 1.0
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取字符串
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置字符串
     * @param key
     * @param value
     */
    public void set(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }
}
