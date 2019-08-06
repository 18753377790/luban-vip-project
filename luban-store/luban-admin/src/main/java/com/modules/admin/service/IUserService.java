package com.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.admin.entity.User;
import com.modules.admin.util.ResponseUtil;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 用户业务层接口
 * @Date 2019/7/30 12:53
 * @Version 1.0
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户
     * @param map
     * @return
     */
    ResponseUtil getUser(Map<String, Object> map);

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    ResponseUtil register(String username, String password);

    /**
     * 获取用户页面
     * @param page
     * @param limit
     * @param map
     * @return
     */
    ResponseUtil getUserPage(int page, int limit, Map<String, Object> map);

    /**
     * 添加用户
     * @param username
     * @param password
     * @param rid
     * @return
     */
    ResponseUtil addUser(String username, String password, int rid);

    /**
     * 获取权限列表
     * @param rid
     * @return
     */
    ResponseUtil getPowerList(int rid);

    /**
     * 获取角色
     * @return
     */
    ResponseUtil getRoles();
}
