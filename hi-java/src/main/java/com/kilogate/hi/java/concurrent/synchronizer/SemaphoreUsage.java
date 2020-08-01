package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 信号量的使用
 *
 * @author kilogate
 * @create 2020/7/30 上午12:07
 **/
public class SemaphoreUsage {
    public static void main(String[] args) {
        int permits = 3;
        Semaphore semaphore = new Semaphore(permits);

        Runnable task = () -> {
            try {
                semaphore.acquire();

                System.out.println(String.format("%s start", Thread.currentThread()));
                Thread.sleep(new Random().nextInt(3000));
                System.out.println(String.format("%s end", Thread.currentThread()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
