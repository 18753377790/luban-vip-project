package com.luban.service;

import com.luban.dao.ServerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 李非凡
 * @Description:
 * 服务二业务层
 * @Date 2019/7/28 0:28
 * @Version 1.0
 */
@Service
public class ServerService {

    @Autowired
    private ServerDao serverDao;

    /**
     * 测试方法
     */
    public void test(){
        serverDao.insert("server2");
    }
}
