package com.hi.spring.ioc.ambiguity.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * App
 *
 * @author fengquanwei
 * @create 2023/10/9 10:31
 **/
@Component
public class App {
    @Resource
    @Qualifier("serviceA") // 方式一：限定 bean id（bean 默认的限定符就是 bean id），缺点：类名变更会导致限定符变更
    private Service service1;

    @Resource
    @Qualifier("BBB") // 方式二：使用自定义限定符（使用 @Qualifier("BBB") 自定义限定符），缺点：自定义的限定符可能会重复
    private Service service2;

    @Resource
    @QualifierC1 // 方式三：使用自定义注解（自定义注解需要使用 @Qualifier 标注，这样他们就具有了 @Qualifier 注解的特性），缺点：麻烦
    @QualifierC2 // 可以组合多个自定义注解来缩小可选 bean 的范围到只有一个 bean 满足需求
    private Service service3;
}
