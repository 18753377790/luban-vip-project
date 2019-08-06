package com.luban.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 李非凡
 * @Description:
 * 服务一持久层接口
 * @Date 2019/7/27 21:22
 * @Version 1.0
 */
@Mapper
public interface ServerDao {

    /**
     * 插入方法
     * @param name
     */
    @Insert("insert into t_test(name) values(#{name})")
    void insert(@Param("name") String name);
}
