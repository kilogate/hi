package com.hi.spring.wire.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Service
 *
 * @author fengquanwei
 * @create 2023/10/25 14:59
 **/
@Component
public class Service {
    // 字面量
    @Value("#{1}")
    private int i;

    // 字面量
    @Value("#{3.14}")
    private float f;

    // 字面量
    @Value("#{'hello'}")
    private String hello;

    // 字面量
    @Value("#{true}")
    private boolean b;

    // 引用类型
    @Value("#{T(java.lang.Math)}")
    private Class clazz;

    // 引用静态常量
    @Value("#{T(java.lang.Math).PI}")
    private double pi;

    // 调用静态方法
    @Value("#{T(System).currentTimeMillis()}")
    private long currentTimeMillis;

    // 引用 bean
    @Value("#{stu}")
    private Stu stu;

    // 引用 bean 的属性（注意属性得是 public 的）
    @Value("#{stu.name}")
    private String name1;

    // 调用 bean 的方法
    @Value("#{stu.getName()?.toUpperCase()}") // ?. 表示类型安全的调用，不会出现 NPE 问题
    private String name2;

    // 通过 systemProperties 引用系统属性
    @Value("#{systemProperties['os.name']}")
    private String osName;

}
