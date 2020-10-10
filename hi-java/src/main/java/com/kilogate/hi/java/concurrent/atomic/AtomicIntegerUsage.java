package com.kilogate.hi.java.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger 的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午3:43
 **/
public class AtomicIntegerUsage {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        System.out.println(atomicInteger.incrementAndGet());
        System.out.println(atomicInteger.decrementAndGet());

        System.out.println(atomicInteger.compareAndSet(0, 10));

        System.out.println(atomicInteger.updateAndGet(prev -> prev + 10));

        System.out.println(atomicInteger.accumulateAndGet(100, (prev, x) -> prev + x));
    }
}
