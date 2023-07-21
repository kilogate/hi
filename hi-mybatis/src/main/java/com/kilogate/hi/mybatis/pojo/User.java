package com.kilogate.hi.mybatis.pojo;

import lombok.Data;

/**
 * User
 *
 * @author kilogate
 * @create 2023/7/17 23:01
 **/
@Data
public class User {
    // id
    private int id;
    // 姓名
    private String name;
    // 密码
    private String pwd;
}
