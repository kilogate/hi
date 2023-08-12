package com.hi.spring.aop.xmlconfig;

/**
 * LogAspect
 *
 * @author kilogate
 * @create 2022/4/28 15:27
 **/
public class LogAspect {
    public void before() {
        System.out.println("before...");
    }

    public void after() {
        System.out.println("after...");
    }
}
