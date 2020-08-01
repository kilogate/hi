package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 循环障栅的使用
 *
 * @author kilogate
 * @create 2020/7/30 上午12:05
 **/
public class CyclicBarrierUsage {
    public static void main(String[] args) {
        int parties = 5;

        Runnable barrierAction = () -> {
            System.out.println(String.format("%24s 人满发车", Thread.currentThread()));
        };

        CyclicBarrier cyclicBarrier = new CyclicBarrier(parties, barrierAction);

        int cyclic = 3;
        for (int i = 0; i < parties * cyclic; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep((long) (3000 * Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(String.format("%24s 已上车, 等待人满发车", Thread.currentThread()));

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(String.format("%24s 已发车", Thread.currentThread()));
            }).start();
        }
    }
}
