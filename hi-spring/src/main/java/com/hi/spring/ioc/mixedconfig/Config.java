package com.hi.spring.ioc.mixedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 混合配置（导入配置）
 *
 * @author fengquanwei
 * @create 2023/10/7 10:31
 **/
@Configuration
@Import({SubConfigA.class, SubConfigB.class}) // 导入 JavaConfig
@ImportResource("SubConfigC.xml") // 导入 XMLConfig
public class Config {
    @Bean
    public Service service(ServiceA serviceA, ServiceB serviceB, ServiceC serviceC) {
        return new Service(serviceA, serviceB, serviceC);
    }
}
