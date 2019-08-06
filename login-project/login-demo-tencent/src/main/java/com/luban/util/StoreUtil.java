package com.luban.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * 存储工具
 * @Date 2019/7/27 11:22
 * @Version 1.0
 */
@Component
public class StoreUtil {

    @Autowired
    private StoreFactory storeFactory;

    /**
     *
     * @param key
     * @return
     */
    public Object get(String key){
        return storeFactory.getStore().get(key);
    }

    /**
     *
     * @param key
     * @param object
     */
    public void set(String key, Object object){
        storeFactory.getStore().set(key, object);
    }
}
