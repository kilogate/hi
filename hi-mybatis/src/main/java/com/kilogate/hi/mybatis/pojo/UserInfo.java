package com.kilogate.hi.mybatis.pojo;

import lombok.Data;

/**
 * UserInfo
 *
 * @author kilogate
 * @create 2023/7/21 00:02
 **/
@Data
public class UserInfo {
    // id
    private int id;
    // 姓名
    private String username;
    // 密码
    private String password;
}
