package com.kilogate.hi.java.concurrent.atomic;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * LongAccumulator 的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午3:44
 **/
public class LongAccumulatorUsage {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left * right, 1);
        System.out.println(longAccumulator.get());

        for (int i = 0; i < 10; i++) {
            longAccumulator.accumulate(2);
            System.out.println(longAccumulator.get());
        }
    }
}
