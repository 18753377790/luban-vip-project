package com.modules.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.admin.entity.Menu;
import com.modules.admin.util.ResponseUtil;

import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 菜单业务层接口
 * @Date 2019/7/25 18:47
 * @Version 1.0
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单信息
     * @return
     */
    ResponseUtil getMenu();

    /**
     * 获取订单信息
     * @param map
     * @return
     */
    ResponseUtil getOrder(Map<String, Object> map);
}
