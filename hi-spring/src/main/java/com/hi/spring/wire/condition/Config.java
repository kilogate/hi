package com.hi.spring.wire.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2023/10/8 11:41
 **/
@Configuration
public class Config {
    @Bean
    @Conditional(MyCondition.class)
    public Service service() {
        return new Service();
    }
}
