package com.luban.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author 李非凡
 * @Description:
 * 服务二持久层接口
 * @Date 2019/7/28 0:40
 * @Version 1.0
 */
@Mapper
public interface ServerDao {

    @Insert("")
    void insert(@Param("") String name);
}
