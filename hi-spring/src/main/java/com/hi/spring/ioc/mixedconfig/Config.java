package com.hi.spring.ioc.mixedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 混合配置（导入配置）
 * 本配置仅演示了在 JavaConfig 中导入 JavaConfig 与 XMLConfig 的案例。
 * 相应的在 XMLConfig 中导入 XMLConfig 使用 <import> 元素即可。
 *
 * @author fengquanwei
 * @create 2023/10/7 10:31
 **/
@Configuration
@Import({SubConfigA.class, SubConfigB.class}) // 导入 JavaConfig（也可以使用组件扫描来导入 JavaConfig：@ComponentScan）
@ImportResource("ioc/mixedconfig/spring.xml") // 导入 XMLConfig
public class Config {
    @Bean
    public Service service(ServiceA serviceA, ServiceB serviceB, ServiceC serviceC) {
        return new Service(serviceA, serviceB, serviceC);
    }
}
