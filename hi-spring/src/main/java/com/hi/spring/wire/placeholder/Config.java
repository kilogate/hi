package com.hi.spring.wire.placeholder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Config
 *
 * @author fengquanwei
 * @create 2023/10/25 14:16
 **/
@Configuration
@ComponentScan
@PropertySource("classpath:wire/property/app.properties") // 声明属性源
public class Config {
    /**
     * 属性源占位符配置器
     *
     * @return
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
