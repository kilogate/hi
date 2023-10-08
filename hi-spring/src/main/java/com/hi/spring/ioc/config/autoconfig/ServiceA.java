package com.hi.spring.ioc.config.autoconfig;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * ServiceA
 *
 * @author kilogate
 * @create 2022/5/4 22:35
 **/
@Component
public class ServiceA {
    @Resource
    private ServiceB serviceB;

    public void hello() {
        System.out.println("hello, ServiceA");
        serviceB.hello();
    }
}
