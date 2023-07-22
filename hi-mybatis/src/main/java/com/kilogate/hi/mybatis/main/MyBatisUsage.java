package com.kilogate.hi.mybatis.main;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.kilogate.hi.mybatis.mapper.UserMapper;
import com.kilogate.hi.mybatis.param.QueryUserParam;
import com.kilogate.hi.mybatis.pojo.User;
import com.kilogate.hi.mybatis.pojo.UserInfo;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * MyBatisUsage
 *
 * @author kilogate
 * @create 2023/7/22 23:28
 **/
@Slf4j
public class MyBatisUsage {
    public static void main(String[] args) throws Exception {
        baseUsage();
    }

    private static void baseUsage() throws Exception {
        // 从XML配置中构建 SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 打开数据库连接
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取 Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 查询数据
        List<User> userList = mapper.getUserList();
        log.info("getUserList end, result: {}", userList);

        // 使用参数查询
        userList = mapper.getUserListByParam(QueryUserParam.builder().nameLike("%三%").passwordLike("%123%").build());
        log.info("getUserListByParam end, result: {}", userList);

        // 更新数据
        User user = User.builder().id(1).name("王三麻子").pwd("123").build();
        int update = mapper.updateUser(user);
        sqlSession.commit();
        log.info("updateUser end, result: {}", update);

        // resultMap
        List<UserInfo> userInfoList = mapper.getUserInfoList();
        log.info("getUserInfoList end, result: {}", userInfoList);

        // foreach 函数
        List<User> users = mapper.selectByIn(ImmutableMap.of("ids", ImmutableList.of(2, 3)));
        log.info("selectByIn end, result: {}", users);
    }
}
