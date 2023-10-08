package com.hi.spring.ioc.config.mixedconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/10/7 10:33
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Service service = context.getBean(Service.class);
        service.hello();
    }
}
