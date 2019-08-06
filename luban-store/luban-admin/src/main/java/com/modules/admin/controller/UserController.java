package com.modules.admin.controller;

import com.config.RedisKeys;
import com.modules.admin.annotation.Power;
import com.modules.admin.service.IUserService;
import com.modules.admin.util.RedisUtil;
import com.modules.admin.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 用户控制器层
 * @Date 2019/7/30 12:26
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录
     * @param map
     * @return
     */
    @RequestMapping("/getUser")
    public Object login(@RequestParam Map<String, Object> map){
        return userService.getUser(map);
    }

    /**
     * 获取全部用户信息
     * @param map
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getAllUser")
    @Power
    public Object getAllUser(Map<String, Object> map, int page, int limit){
        return userService.getUserPage(page, limit, map);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/register")
    public Object register(String username, String password){
        return userService.register(username, password);
    }

    /**
     * 添加用户
     * @param username
     * @param password
     * @param rid
     * @return
     */
    @RequestMapping("/addUser")
    public Object addUser(String username, String password, int rid){
        return userService.addUser(username, password, rid);
    }

    /**
     * 获取权限列表
     * @param request
     * @return
     */
    @RequestMapping("/getPowerList")
    public Object getPowerList(HttpServletRequest request){
        String login_key = request.getHeader(RedisKeys.LOGIN_KEY);
        if (StringUtils.isEmpty(login_key)){
            return ResponseUtil.error("用户未登录");
        }
        Map<String, Object> user = (Map<String, Object>) redisUtil.get(login_key);
        if (user == null){
            return ResponseUtil.error("用户未登录");
        }
        return userService.getPowerList((Integer) user.get("rid"));
    }

    /**
     * 获取角色
     * @return
     */
    @RequestMapping("/getRoles")
    public Object getRoles(){
        return userService.getRoles();
    }
}
