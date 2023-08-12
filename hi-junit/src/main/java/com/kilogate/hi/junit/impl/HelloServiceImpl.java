package com.kilogate.hi.junit.impl;

import com.kilogate.hi.junit.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * HelloServiceImpl
 *
 * @author kilogate
 * @create 2023/8/12 21:12
 **/
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
        log.info("sayHello start");
        log.info("sayHello end");
    }

    @Override
    public String getHelloMsg(String name) {
        log.info("getHelloMsg start");
        log.info("getHelloMsg end");
        return String.format("hello %s", name);
    }
}
