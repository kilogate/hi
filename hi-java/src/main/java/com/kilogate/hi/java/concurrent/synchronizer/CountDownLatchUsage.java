package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时门栓的使用
 *
 * @author fengquanwei
 * @create 2020/7/30 上午12:06
 **/
public class CountDownLatchUsage {
    public static void main(String[] args) {
        usage2();
    }

    /**
     * 等待其他任务都完成之后再继续执行
     */
    private static void usage1() {
        System.out.println("done?");

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
                System.out.println(String.format("%s %s done", System.nanoTime(), Thread.currentThread()));
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

        System.out.println("run!");
    }

    /**
     * 让所有任务同时执行（模拟并发请求）
     */
    private static void usage2() {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Runnable task = () -> {
            try {
                // wait for execute command
                countDownLatch.await();

                // work
                System.out.println(String.format("%s %s begin", new Date(), Thread.currentThread()));
                Thread.sleep((long) (3000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        int concurrencyCount = 100;
        for (int i = 0; i < concurrencyCount; i++) {
            new Thread(task).start();
        }

        countDownLatch.countDown();

        System.out.println("begin");
    }
}
