package com.hi.spring.ioc.autoconfig;

import org.springframework.stereotype.Component;

/**
 * Service4AutoConfig
 *
 * @author fengquanwei
 * @create 2022/5/4 22:35
 **/
@Component
public class Service4AutoConfig {
    public void sayHello() {
        System.out.println("hello, autoconfig");
    }
}
