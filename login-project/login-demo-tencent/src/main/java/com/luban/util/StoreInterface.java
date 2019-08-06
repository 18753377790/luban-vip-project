package com.luban.util;

/**
 * @Author 李非凡
 * @Description:
 * 存储接口
 * @Date 2019/7/27 11:18
 * @Version 1.0
 */
public interface StoreInterface {

    /**
     * 获取存储
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 设置存储
     * @param key
     * @param object
     */
    void set(String key, Object object);
}
