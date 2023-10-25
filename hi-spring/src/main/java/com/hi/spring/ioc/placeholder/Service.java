package com.hi.spring.ioc.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Service
 *
 * @author fengquanwei
 * @create 2023/10/25 14:23
 **/
@Component
public class Service {
    /**
     * 通过占位符装配属性（占位符的值来源于：属性源 & Spring Environment）
     * 必须要配置一个 PropertyPlaceholderConfigurer bean 或 PropertySourcesPlaceholderConfigurer bean
     * 推荐后者，因为它能够基于 Spring Environment 及其属性源来解析占位符
     */
    @Value("${name}")
    private String name;

    @Value("${age}")
    private Integer age;

    public void sayHi() {
        System.out.println(String.format("Hi, %s(%d)", name, age));
    }
}
