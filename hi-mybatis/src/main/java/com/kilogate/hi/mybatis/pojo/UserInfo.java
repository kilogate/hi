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
    private int id;  //id
    private String username;   //姓名
    private String password;   //密码
}
