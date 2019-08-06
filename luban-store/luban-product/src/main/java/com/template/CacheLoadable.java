package com.template;

/**
 * @Author 李非凡
 * @Description:
 * 可加载缓存接口
 * @Date 2019/7/24 11:31
 * @Version 1.0
 */
public interface CacheLoadable<T> {

    /**
     * 加载
     * @return
     */
    T load();
}
