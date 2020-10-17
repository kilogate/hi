package com.kilogate.hi.java.concurrent.threadLocal;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ThreadLocalRandomUsage
 *
 * @author kilogate
 * @create 2020/8/1 下午3:45
 **/
public class ThreadLocalRandomUsage {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        long start = System.currentTimeMillis();
        System.out.println("start: main");

        CompletableFuture<Void>[] completableFutures = new CompletableFuture[200];
        for (int i = 0; i < 200; i++) {
            int finalI = i;
            completableFutures[i] = CompletableFuture.runAsync(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread() + " start: " + finalI);
                for (int j = 0; j < 1000000; j++) {
                    random.nextInt();
                }
                System.out.println(Thread.currentThread() + " end: " + finalI);
            });
        }

        countDownLatch.countDown();
        CompletableFuture.allOf(completableFutures).join();

        long end = System.currentTimeMillis();
        System.out.println("end: main");
        System.out.println("cost: " + (end - start));
    }
}
