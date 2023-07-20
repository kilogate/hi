package com.kilogate.hi.mybatis.dao;

import com.kilogate.hi.mybatis.pojo.User;

import java.util.List;

/**
 * UserDao
 *
 * @author kilogate
 * @create 2023/7/17 23:02
 **/
public interface UserDao {
    List<User> getUserList();
}
