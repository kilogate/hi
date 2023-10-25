package com.hi.spring.wire.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * ServiceC
 *
 * @author fengquanwei
 * @create 2023/10/7 10:28
 **/
@Component
@Profile("C")
public class ServiceC {
    public void hello() {
        System.out.println("hello, ServiceC");
    }
}
