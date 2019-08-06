package com.modules.admin.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 李非凡
 * @Description:
 * 菜单属性结构
 * @Date 2019/7/25 17:54
 * @Version 1.0
 */
public class MenuTree {
    // id
    private String id;
    // 名字
    private String name;
    // 类型
    private int type;
    //
    private String href;
    //
    private List<MenuTree> children;

    /**
     *  初始化子菜单
     */
    public void initChild(){
        if (this.children==null) {
            this.children = new ArrayList<>();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }
}
