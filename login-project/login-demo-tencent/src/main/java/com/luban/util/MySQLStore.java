package com.luban.util;

import org.springframework.stereotype.Component;

/**
 * @Author 李非凡
 * @Description:
 * MySQL存储
 * @Date 2019/7/27 11:22
 * @Version 1.0
 */
@Component
public class MySQLStore implements StoreInterface {

    @Override
    public Object get(String key) {
        System.out.println("把信息存入数据库");
        return null;
    }

    @Override
    public void set(String key, Object object) {
        System.out.println("从数据库读取信息");
    }
}
