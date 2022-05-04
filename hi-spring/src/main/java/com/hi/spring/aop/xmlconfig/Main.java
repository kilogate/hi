package com.hi.spring.aop.xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2022/4/28 15:37
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Service service = applicationContext.getBean(Service.class);
        service.service();
    }
}
