package com.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author 李非凡
 * @Description:
 * 商品目录实体类
 * @Date 2019/7/24 19:27
 * @Version 1.0
 */
public class Catalog implements Serializable {

    private static final long serialVersionUID = 6050481905721781179L;

    /**
     * 分类ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父ID
     */
    private Integer pid;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 类型 : a:文章目录,p:产品目录
     */
    private String type;

    /**
     * 是否显示在首页导航条上 y:显示 n:不显示  默认: n
     */
    @TableField("showInNav")
    private String showInNav;

    public Integer getId() {
        return id;
    }

    public Catalog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Catalog setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public Catalog setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Catalog setOrder(Integer order) {
        this.order = order;
        return this;
    }

    public String getType() {
        return type;
    }

    public Catalog setType(String type) {
        this.type = type;
        return this;
    }

    public String getShowInNav() {
        return showInNav;
    }

    public Catalog setShowInNav(String showInNav) {
        this.showInNav = showInNav;
        return this;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name=" + name +
                ", pid=" + pid +
                ", order=" + order +
                ", type=" + type +
                ", showInNav=" + showInNav +
                "}";
    }
}
