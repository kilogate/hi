package com.hi.spring.wire.profile;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 激活 profile
 * Spring 在确定哪个 profile 处于激活状态时，需要依赖两个独立的属性
 * spring.profiles.active 和 spring.profiles.default
 * 如果设置了 spring.profiles.active 属性的话，那么它的值就会用来确定哪个profile是激活的。
 * 但如果没有设置 spring.profiles.active 属性的话，那 Spring 将会查找 spring.profiles.default 的值。
 * 如果 spring.profiles.active 和 spring.profiles.default 均没有设置的话，那就没有激活的 profile，因此只会创建那些没有定义在 profile 中的 bean。
 * <p>
 * 有多种方式来设置这两个属性:
 * 1、作为 DispatcherServlet 的初始化参数;
 * 2、作为 Web 应用的上下文参数;
 * 3、作为 JNDI 条目;
 * 4、作为环境变量;
 * 5、作为 JVM 的系统属性;
 * 6、在集成测试类上，使用 @ActiveProfiles 注解设置。
 *
 * @author fengquanwei
 * @create 2023/10/7 10:33
 **/
public class Main {
    public static void main(String[] args) {
        // 运行时需要设置环境变量：spring.profiles.active=A,B,C
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        ServiceA serviceA = context.getBean(ServiceA.class);
        ServiceB serviceB = context.getBean(ServiceB.class);
        ServiceC serviceC = context.getBean(ServiceC.class);
        ServiceD serviceD = context.getBean(ServiceD.class);

        System.out.println("serviceA = " + serviceA);
        System.out.println("serviceB = " + serviceB);
        System.out.println("serviceC = " + serviceC);
        System.out.println("serviceD = " + serviceD);
    }
}
