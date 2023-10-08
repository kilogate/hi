package com.hi.spring.ioc.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * SubConfigB
 *
 * @author fengquanwei
 * @create 2023/10/7 10:27
 **/
@Configuration
public class SubConfigB {
    @Bean
    @Profile("B")
    public ServiceB serviceB() {
        return new ServiceB();
    }
}
