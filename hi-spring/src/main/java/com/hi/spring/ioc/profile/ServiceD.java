package com.hi.spring.ioc.profile;

import org.springframework.stereotype.Component;

/**
 * ServiceD
 *
 * @author fengquanwei
 * @create 2023/10/7 10:28
 **/
@Component
public class ServiceD {
    public void hello() {
        System.out.println("hello, ServiceD");
    }
}
