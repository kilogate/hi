package com.kilogate.hi.java.spi;

import java.util.ServiceLoader;

/**
 * SPI 的用法
 *
 * @author kilogate
 * @create 2021/1/2 22:51
 **/
public class SpiUsage {
    public static void main(String[] args) {
        ServiceLoader<SayHiService> serviceLoader = ServiceLoader.load(SayHiService.class);

        for (SayHiService sayHiService : serviceLoader) {
            sayHiService.sayHi();
        }
    }
}
