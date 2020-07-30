package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatchUsage
 *
 * @author fengquanwei
 * @create 2020/7/30 上午12:06
 **/
public class CountDownLatchUsage {
    public static void main(String[] args) {
        System.out.println("ready?");

        int taskCount = 7;
        CountDownLatch countDownLatch = new CountDownLatch(taskCount);

        Runnable task = () -> {
            try {
                // work
                Thread.sleep((long) (3000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // count down
                System.out.println(String.format("%s ready", Thread.currentThread()));
                countDownLatch.countDown();
            }
        };

        for (int i = 0; i < taskCount; i++) {
            new Thread(task).start();
        }

        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("go!");
    }
}
