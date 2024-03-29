package com.hi.spring.ioc.xmlconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Main
 *
 * @author kilogate
 * @create 2022/4/28 15:00
 **/
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ioc/xmlconfig/spring.xml");
        ServiceA serviceA = applicationContext.getBean(ServiceA.class);
        serviceA.hello();
    }
}
