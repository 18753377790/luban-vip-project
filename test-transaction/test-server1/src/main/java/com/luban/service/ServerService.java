package com.luban.service;

import com.luban.dao.ServerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author 李非凡
 * @Description:
 * 服务一业务层
 * @Date 2019/7/27 21:24
 * @Version 1.0
 */
@Service
public class ServerService {

    @Autowired
    private ServerDao serverDao;

    /**
     * 测试方法，向数据库表中插入"server1"，同时让server2往数据库插入"server2"，
     * 要么同时成功，要么同时失败
     */
    @Transactional
    public void test(){
        serverDao.insert("server1");

        // 模拟异常ArithmeticException
        int i = 1/0;
    }
}
