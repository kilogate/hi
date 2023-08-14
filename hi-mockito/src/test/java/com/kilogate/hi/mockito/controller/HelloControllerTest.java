package com.kilogate.hi.mockito.controller;

import com.kilogate.hi.mockito.service.HelloService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

/**
 * HelloControllerTest
 *
 * @author fengquanwei
 * @create 2023/8/14 10:53
 **/
class HelloControllerTest {
    @Mock
    private HelloService helloService;

    @InjectMocks
    private HelloController helloController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(helloService.getHelloMsg(anyString())).thenReturn("hello hello");
    }

    @Test
    void getHelloMsg() {
        String msg = helloController.getHelloMsg("a");
        Assertions.assertEquals("hello hello", msg);
    }
}