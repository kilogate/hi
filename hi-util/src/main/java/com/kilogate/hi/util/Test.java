package com.kilogate.hi.util;

import com.kilogate.hi.java.test.HiService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Test
 *
 * @author fengquanwei
 * @create 2020/7/5 下午11:13
 **/
public class Test {
    public static void main(String[] args) {
        ServiceLoader<HiService> load = ServiceLoader.load(HiService.class);

        for (HiService hiService : load) {
            hiService.sayHello();
        }

        System.out.println("done");
    }
}
