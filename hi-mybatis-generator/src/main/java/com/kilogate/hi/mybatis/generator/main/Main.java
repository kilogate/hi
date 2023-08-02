package com.kilogate.hi.mybatis.generator.main;

import com.kilogate.hi.mybatis.generator.mapper.UserMapper;
import com.kilogate.hi.mybatis.generator.model.User;
import com.kilogate.hi.mybatis.generator.model.UserExample;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Main
 *
 * @author kilogate
 * @create 2023/8/2 19:10
 **/
@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
        // 从 XML 配置中构建 SqlSessionFactory
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 打开数据库连接
        @Cleanup SqlSession sqlSession = sqlSessionFactory.openSession();

        // 获取 Mapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        // 查询数据
        List<User> list = mapper.selectByExample(null);
        log.info("selectByExample end, res: {}", list);

        UserExample example = new UserExample();
        example.createCriteria().andNameLike("%三%");

        list = mapper.selectByExample(example);
        log.info("selectByExample end, res: {}", list);
    }
}
