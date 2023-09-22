package com.hi.spring.ioc.xmlconfig;

/**
 * ServiceA
 *
 * @author kilogate
 * @create 2022/4/28 15:06
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
