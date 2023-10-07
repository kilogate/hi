package com.hi.spring.ioc.mixedconfig.javaMxml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SubConfigB
 *
 * @author fengquanwei
 * @create 2023/10/7 10:27
 **/
@Configuration
public class SubConfigB {
    @Bean
    public ServiceB serviceB() {
        return new ServiceB();
    }
}
