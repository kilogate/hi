package com.hi.spring.ioc.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * SubConfigA
 *
 * @author fengquanwei
 * @create 2023/10/7 10:27
 **/
@Configuration
@Profile("A")
public class SubConfigA {
    @Bean
    public ServiceA serviceA() {
        return new ServiceA();
    }
}
