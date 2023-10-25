package com.hi.spring.wire.list;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2023/10/20 15:29
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        App app = applicationContext.getBean(App.class);
        System.out.println(app);
    }
}
