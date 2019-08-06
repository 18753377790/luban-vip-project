package com.modules.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.modules.admin.entity.User;
import com.modules.admin.mapper.MenuMapper;
import com.modules.admin.mapper.RoleMapper;
import com.modules.admin.mapper.UserMapper;
import com.modules.admin.service.IUserService;
import com.modules.admin.util.RedisUtil;
import com.modules.admin.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 用户业务层实现类
 * @Date 2019/7/30 12:27
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResponseUtil getUser(Map<String, Object> map) {
        List<User> users = userMapper.selectByMap(map);
        if (users.size() > 0){
            return ResponseUtil.success("success", users.get(0));
        }
        return ResponseUtil.success("success", null);
    }

    @Override
    public ResponseUtil register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatetime(new Date());
        return ResponseUtil.success("success", userMapper.insert(user));
    }

    @Override
    public ResponseUtil getUserPage(int page, int limit, Map<String, Object> map) {
        QueryWrapper queryWrapper = new QueryWrapper();
        map.remove("page");
        map.remove("limit");
        queryWrapper.allEq(map, false);
        Page<User> userPage = new Page<>(page, limit);
        IPage iPage = userMapper.selectPage(userPage, queryWrapper);
        return ResponseUtil.success("success", iPage.getRecords()).set("count", iPage.getTotal());
    }

    @Override
    public ResponseUtil addUser(String username, String password, int rid) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setCreatetime(new Date());
        user.setRid(rid);
        return ResponseUtil.success("success").data(userMapper.insert(user));
    }

    @Override
    public ResponseUtil getPowerList(int pid) {
        List<String> list = menuMapper.selectMenuPower(pid);
        redisUtil.set(pid + "", list);
        return ResponseUtil.success("success", list);
    }

    @Override
    public ResponseUtil getRoles() {
        return ResponseUtil.success("success", roleMapper.selectByMap(null));
    }
}
