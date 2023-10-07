package com.hi.spring.ioc.mixedconfig;

/**
 * Service
 *
 * @author fengquanwei
 * @create 2023/10/7 10:28
 **/
public class Service {
    private ServiceA serviceA;
    private ServiceB serviceB;
    private ServiceC serviceC;

    public Service(ServiceA serviceA, ServiceB serviceB, ServiceC serviceC) {
        this.serviceA = serviceA;
        this.serviceB = serviceB;
        this.serviceC = serviceC;
    }

    public void hello() {
        serviceA.hello();
        serviceB.hello();
        serviceC.hello();
        System.out.println("hello, Service");
    }
}
