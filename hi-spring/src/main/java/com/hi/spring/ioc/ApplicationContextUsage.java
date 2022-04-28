package com.hi.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ApplicationContextUsage
 *
 * @author fengquanwei
 * @create 2022/4/28 15:00
 **/
public class ApplicationContextUsage {
    public static void main(String[] args) {
        // ClassPathXmlApplicationContext
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ServiceA serviceA = applicationContext.getBean(ServiceA.class);
        serviceA.sayHello();
    }
}
