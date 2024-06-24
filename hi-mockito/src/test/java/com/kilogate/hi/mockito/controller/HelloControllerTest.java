package com.kilogate.hi.mockito.controller;

import com.kilogate.hi.mockito.service.HelloService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * HelloControllerTest
 *
 * @author fengquanwei
 * @create 2023/8/14 10:53
 **/
class HelloControllerTest {
    @InjectMocks
    private HelloController helloController;

    @Mock
    private HelloService helloService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(helloService.getHelloMsg(Mockito.anyString())).thenReturn("hello hello");
    }

    @Test
    void getHelloMsg() {
        String msg = helloController.getHelloMsg("a");
        Assertions.assertEquals("hello hello", msg);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset();
    }
}