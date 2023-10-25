package com.hi.spring.ioc.property;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2023/10/25 14:16
 **/
@Configuration
@PropertySource("classpath:ioc/property/app.properties") // 声明属性源
public class Config {
    @Resource
    private Environment env;

    @Bean
    public Stu stu() {
        String name = env.getProperty("name"); // 检索属性值
        Integer age = env.getProperty("age", int.class);
        return new Stu(name, age);
    }
}
