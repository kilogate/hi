package com.kilogate.hi.mybatis.dao;

import com.kilogate.hi.mybatis.param.QueryUserParam;
import com.kilogate.hi.mybatis.pojo.User;
import com.kilogate.hi.mybatis.pojo.UserInfo;

import java.util.List;
import java.util.Map;

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

    int updateUser(User user);

    List<User> selectByIn(Map<String, Object> param);
}
