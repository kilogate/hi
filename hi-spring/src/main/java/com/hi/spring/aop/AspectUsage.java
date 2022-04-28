package com.hi.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * AspectUsage
 *
 * @author fengquanwei
 * @create 2022/4/28 15:37
 **/
public class AspectUsage {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Service service = applicationContext.getBean(Service.class);
        service.service();
    }
}
