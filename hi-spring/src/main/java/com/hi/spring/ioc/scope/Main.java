package com.hi.spring.ioc.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/10/24 14:16
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        for (int i = 0; i < 10; i++) {
            System.out.println("----------- " + i + " -----------");

            // 单例bean：在整个应用中，只创建bean的一个实例
            ServiceA serviceA = applicationContext.getBean(ServiceA.class);
            System.out.println(serviceA);

            // 原型bean：每次注入或者通过Spring应用上下文获取的时候，都会创建一个新的bean实例
            ServiceB serviceB = applicationContext.getBean(ServiceB.class);
            System.out.println(serviceB);
        }
    }
}
