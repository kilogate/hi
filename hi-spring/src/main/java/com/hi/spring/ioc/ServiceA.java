package com.hi.spring.ioc;

/**
 * ServiceA
 *
 * @author fengquanwei
 * @create 2022/4/28 15:06
 **/
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    public void sayHello() {
        serviceB.sayHello();
    }
}
