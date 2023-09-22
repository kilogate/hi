package com.hi.spring.ioc.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/9/22 19:50
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ServiceA serviceA = applicationContext.getBean(ServiceA.class);
        serviceA.hello();
    }
}
