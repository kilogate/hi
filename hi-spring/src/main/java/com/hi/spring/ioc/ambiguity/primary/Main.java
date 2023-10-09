package com.hi.spring.ioc.ambiguity.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/10/9 10:21
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Service bean = applicationContext.getBean(Service.class);
        System.out.println(bean);
    }
}
