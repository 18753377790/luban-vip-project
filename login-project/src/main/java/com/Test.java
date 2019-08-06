package com;

/**
 * @Author 李非凡
 * @Description:
 * 测试类
 * @Date 2019/7/26 9:44
 * @Version 1.0
 */
public class Test {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到");
            e.printStackTrace();
        }
    }
}
