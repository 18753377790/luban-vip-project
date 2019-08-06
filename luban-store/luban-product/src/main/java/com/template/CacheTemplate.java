package com.template;

import com.modules.product.util.ResponseUtil;
import com.systemConst.CacheConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author 李非凡
 * @Description:
 * 缓存模板
 * @Date 2019/7/24 11:34
 * @Version 1.0
 */
@Component
public class CacheTemplate<T> {

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private RedisLock redisLock;

    /**
     * 查询Redis缓存
     * @param key
     * @param expire
     * @param unit
     * @param cacheLoadable
     * @return
     */
    public ResponseUtil findCache(String key, long expire, TimeUnit unit, CacheLoadable<T> cacheLoadable){
        // 查询商品缓存
        Object redisObj = valueOperations.get(CacheConst.PRODUCT_CACHE_KEY + String.valueOf(key));
        if (redisObj != null){
            return ResponseUtil.success("success", redisObj);
        }
        try {
            redisLock.lock(key);
            redisObj = valueOperations.get(String.valueOf(key));
            if (redisObj != null){
                return ResponseUtil.success();
            }
            T load = cacheLoadable.load();
            // 加入缓存
            valueOperations.set(CacheConst.PRODUCT_CACHE_KEY + String.valueOf(key), load, expire, unit);
            return ResponseUtil.success("success", load);
        }finally {
            redisLock.unLock(key);
        }
    }
}
