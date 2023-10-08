package com.hi.spring.ioc.config.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config
 *
 * @author kilogate
 * @create 2022/5/4 22:35
 **/
@Configuration
public class Config {
    @Bean
    public ServiceB serviceB() {
        return new ServiceB();
    }

    @Bean
    public ServiceA serviceA() {
        return new ServiceA(serviceB());
    }
}
