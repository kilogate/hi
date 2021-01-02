package com.kilogate.hi.java.spi;

/**
 * 接口实现
 *
 * @author kilogate
 * @create 2021/1/2 22:48
 **/
public class SayHiServiceImpl implements SayHiService {
    @Override
    public void sayHi() {
        System.out.println("hi");
    }
}
