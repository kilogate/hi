package com.kilogate.hi.mybatis.dao;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.kilogate.hi.mybatis.param.QueryUserParam;
import com.kilogate.hi.mybatis.pojo.User;
import com.kilogate.hi.mybatis.pojo.UserInfo;
import com.kilogate.hi.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * UserDaoTest
 *
 * @author kilogate
 * @create 2023/7/17 23:06
 **/
public class UserDaoTest {
    SqlSession sqlSession;

    @Test
    public void test() {
        try {
            sqlSession = MybatisUtils.getSqlSession();
            UserDao mapper = sqlSession.getMapper(UserDao.class);

            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }

            userList = mapper.getUserListByParam(QueryUserParam.builder().nameLike("%三%").passwordLike("%123%").build());
            for (User user : userList) {
                System.out.println(user);
            }

            User user = User.builder().id(1).name("王三").pwd("123").build();
            int update = mapper.updateUser(user);
            System.out.println(update);
            sqlSession.commit();

            List<UserInfo> userInfoList = mapper.getUserInfoList();
            for (UserInfo userInfo : userInfoList) {
                System.out.println(userInfo);
            }

            List<User> users = mapper.selectByIn(ImmutableMap.of("ids", ImmutableList.of(2, 3)));
            System.out.println(users);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}