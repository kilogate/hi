package com.kilogate.hi.java.init;

/**
 * 类初始化顺序测试
 *
 * @author kilogate
 * @create 2020/4/2 下午11:06
 **/
public class T {
    public static void main(String[] args) {
        new S();
        new S();
        new S();
    }
}
