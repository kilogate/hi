package com.hi.spring;

import com.kilogate.hi.java.test.HiService;

/**
 * ImplA
 *
 * @author fengquanwei
 * @create 2020/7/5 下午11:11
 **/
public class ImplA implements HiService {
    @Override
    public void sayHello() {
        System.out.println("ImplA: Hi");
    }
}
