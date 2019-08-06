package com.modules.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.modules.admin.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * @Author 李非凡
 * @Description:
 * 菜单持久层接口
 * @Date 2019/7/25 18:42
 * @Version 1.0
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过角色获取菜单
     * @param map
     * @return
     */
    List<Menu> getMenuByRole(Map<String, Object> map);

    /**
     * 查询菜单权限
     * @param id
     * @return
     */
    List<String> selectMenuPower(int id);
}
