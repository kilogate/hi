package com.hi.spring.ioc.autoconfig;

import org.springframework.stereotype.Component;

/**
 * ServiceB
 *
 * @author fengquanwei
 * @create 2023/9/22 19:55
 **/
@Component
public class ServiceB {
    public void hello() {
        System.out.println("hello, ServiceB");
    }
}
