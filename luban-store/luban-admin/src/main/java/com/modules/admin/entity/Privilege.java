package com.modules.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * @Author 李非凡
 * @Description:
 * 基本权利实体类
 * @Date 2019/7/25 17:54
 * @Version 1.0
 */
public class Privilege implements Serializable {

    private static final long serialVersionUID = 4071837394025355102L;

    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 资源id
     */
    private Integer mid;

    public Integer getId() {
        return id;
    }

    public Privilege setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getRid() {
        return rid;
    }

    public Privilege setRid(Integer rid) {
        this.rid = rid;
        return this;
    }

    public Integer getMid() {
        return mid;
    }

    public Privilege setMid(Integer mid) {
        this.mid = mid;
        return this;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", rid=" + rid +
                ", mid=" + mid +
                "}";
    }
}
