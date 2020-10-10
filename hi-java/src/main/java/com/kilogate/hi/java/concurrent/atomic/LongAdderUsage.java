package com.kilogate.hi.java.concurrent.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * LongAdder 的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午3:44
 **/
public class LongAdderUsage {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        System.out.println(longAdder.sum());

        longAdder.increment();
        System.out.println(longAdder.sum());

        longAdder.add(100);
        System.out.println(longAdder.sum());
    }
}
