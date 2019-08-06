package com.modules.admin.controller;

import com.modules.admin.service.IMenuService;
import com.modules.admin.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 菜单控制器层
 * @Date 2019/7/25 19:24
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单信息
     * @return
     */
    @RequestMapping("/getMenu")
    public ResponseUtil getMenu(){
        return menuService.getMenu();
    }

    /**
     * 获取订单信息
     * @param map
     * @return
     */
    @RequestMapping("/getOrder")
    public ResponseUtil getOrder(@RequestParam Map<String, Object> map){
        return menuService.getOrder(map);
    }
}
