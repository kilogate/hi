package com.kilogate.hi.mockito.service;

/**
 * HelloService
 *
 * @author fengquanwei
 * @create 2023/8/14 10:52
 **/
public class HelloService {
    public String getHelloMsg(String name) {
        return String.format("hello %s", name);
    }
}
