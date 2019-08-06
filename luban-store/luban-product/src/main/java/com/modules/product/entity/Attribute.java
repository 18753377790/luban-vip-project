package com.modules.product.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author 李非凡
 * @Description:
 * 商品属性实体类
 * @Date 2019/7/24 19:29
 * @Version 1.0
 */
public class Attribute implements Serializable {

    private static final long serialVersionUID = -9186691013531532018L;

    /**
     * 属性id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 属性名字
     */
    private String name;

    /**
     * 类型Id
     */
    @TableField("catalogID")
    private Integer catalogID;

    /**
     * 父id
     */
    private Integer pid;

    /**
     * 排序字段
     */
    private Integer order;

    public Integer getId() {
        return id;
    }

    public Attribute setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Attribute setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCatalogID() {
        return catalogID;
    }

    public Attribute setCatalogID(Integer catalogID) {
        this.catalogID = catalogID;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public Attribute setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getOrder() {
        return order;
    }

    public Attribute setOrder(Integer order) {
        this.order = order;
        return this;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "id=" + id +
                ", name=" + name +
                ", catalogID=" + catalogID +
                ", pid=" + pid +
                ", order=" + order +
                "}";
    }
}
