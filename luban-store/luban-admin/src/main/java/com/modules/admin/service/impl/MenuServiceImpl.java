package com.modules.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.modules.admin.entity.Menu;
import com.modules.admin.entity.MenuTree;
import com.modules.admin.feginService.OrderServiceClient;
import com.modules.admin.mapper.MenuMapper;
import com.modules.admin.service.IMenuService;
import com.modules.admin.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 菜单业务层实现类
 * @Date 2019/7/25 18:50
 * @Version 1.0
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private OrderServiceClient orderServiceClient;

    @Override
    public ResponseUtil getMenu() {
        return ResponseUtil.success("success", getRootMenu());
    }

    @Override
    public ResponseUtil getOrder(Map<String, Object> map) {
        return orderServiceClient.getOrder(map.get("page"), map.get("limit"));
    }

    /**
     * 获取根（一级）菜单
     * @return
     */
    private List<MenuTree> getRootMenu(){
        Map<String, Object> map = new HashMap<>();
        map.put("pid", 0);
        List<Menu> menus = menuMapper.selectByMap(map);
        List<MenuTree> list = new ArrayList<>();
        for (Menu menu : menus) {
            MenuTree menuTree = new MenuTree();
            menuTree.setId(menu.getId() + "");
            menuTree.setHref(menu.getUrl());
            menuTree.setName(menu.getName());
            list.add(menuTree);
            getChildMenu(menuTree);
        }
        return list;
    }

    /**
     * 获取子（二级）菜单
     * @param menuTree
     */
    private void getChildMenu(MenuTree menuTree){
        String id = menuTree.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("pid",id);
        List<Menu> menus = menuMapper.selectByMap(map);
        for (Menu menu : menus) {
            menuTree.initChild();
            MenuTree menuTree1 = new MenuTree();
            menuTree1.setId(menu.getId() + "");
            menuTree1.setHref(menu.getUrl());
            menuTree1.setName(menu.getName());
            menuTree.getChildren().add(menuTree1);
            getChildMenu(menuTree1);
        }
    }
}
