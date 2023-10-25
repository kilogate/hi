package com.hi.spring.ioc.placeholder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/10/25 14:25
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Service service = applicationContext.getBean(Service.class);
        service.sayHi();
    }
}
