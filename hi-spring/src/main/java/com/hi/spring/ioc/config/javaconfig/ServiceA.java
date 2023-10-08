package com.hi.spring.ioc.config.javaconfig;

/**
 * ServiceA
 *
 * @author kilogate
 * @create 2022/5/4 22:35
 **/
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void hello() {
        System.out.println("hello, ServiceA");
        serviceB.hello();
    }
}
