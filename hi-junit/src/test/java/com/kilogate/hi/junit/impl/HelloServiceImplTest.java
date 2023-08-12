package com.kilogate.hi.junit.impl;

import com.kilogate.hi.junit.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

/**
 * HelloServiceImplTest
 *
 * @author fengquanwei
 * @create 2023/8/12 21:16
 **/
@Slf4j
class HelloServiceImplTest {
    @BeforeAll
    public static void init() {
        log.info("init...");
    }

    @AfterAll
    public static void cleanup() {
        log.info("cleanup...");
    }

    @BeforeEach
    void setUp() {
        log.info("setUp...");
    }

    @AfterEach
    void tearDown() {
        log.info("tearDown...");
    }

    @Test
    void sayHello() {
        HelloService helloService = new HelloServiceImpl();
        helloService.sayHello();
    }

    @Test
    void getHelloMsg() {
        HelloService helloService = new HelloServiceImpl();
        helloService.getHelloMsg("abc");
    }
}