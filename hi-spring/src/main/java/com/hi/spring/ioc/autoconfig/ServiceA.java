package com.hi.spring.ioc.autoconfig;

import org.springframework.stereotype.Component;

/**
 * ServiceA
 *
 * @author kilogate
 * @create 2022/5/4 22:35
 **/
@Component
public class ServiceA {
    public void hello() {
        System.out.println("hello! auto config ioc");
    }
}
