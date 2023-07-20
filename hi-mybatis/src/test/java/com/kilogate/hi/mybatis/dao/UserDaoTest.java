package com.kilogate.hi.mybatis.dao;

import com.kilogate.hi.mybatis.pojo.User;
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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}