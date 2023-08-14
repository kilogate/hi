package com.kilogate.hi.mockito.controller;

import com.kilogate.hi.mockito.service.HelloService;

/**
 * HelloController
 *
 * @author fengquanwei
 * @create 2023/8/14 10:53
 **/
public class HelloController {
    private HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String getHelloMsg(String name) {
        return helloService.getHelloMsg(name);
    }
}
