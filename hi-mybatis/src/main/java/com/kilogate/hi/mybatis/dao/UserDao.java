package com.kilogate.hi.mybatis.dao;

import com.kilogate.hi.mybatis.param.QueryUserParam;
import com.kilogate.hi.mybatis.pojo.User;
import com.kilogate.hi.mybatis.pojo.UserInfo;

import java.util.List;

/**
 * UserDao
 *
 * @author kilogate
 * @create 2023/7/17 23:02
 **/
public interface UserDao {
    List<User> getUserList();

    List<UserInfo> getUserInfoList();

    List<User> getUserListByParam(QueryUserParam param);
}
