package com.luban.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * 存储工厂
 * @Date 2019/7/27 11:22
 * @Version 1.0
 */
@Component
public class StoreFactory {

    @Autowired
    private MySQLStore mySQLStore;

    @Autowired
    private RedisStore redisStore;

    @Value("${luban.storeType}")
    private String storeType;

    /**
     *
     * @return
     */
    public StoreInterface getStore(){
        switch (storeType){
            case "reids":
                return redisStore;
            case "mysql":
                return mySQLStore;
        }
        return redisStore;
    }
}
