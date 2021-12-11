package com.kilogate.hi.java.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;

/**
 * FutureTaskUsage
 *
 * @author kilogate
 * @create 2021/12/11 21:05
 **/
public class FutureTaskUsage {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        System.out.printf("[%s]main: start%n", Thread.currentThread());

        Runnable task1 = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.printf("[%s]task1: %s%n", Thread.currentThread(), i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        FutureTask<String> futureTask = new FutureTask<>(task1, "success");

        Thread thread1 = new Thread(futureTask);
        thread1.start();

        String result = futureTask.get();
        System.out.println(result);
        System.out.println("isDone: " + futureTask.isDone());

//        futureTask.get(3, TimeUnit.SECONDS);

//        Thread.sleep(3000);
//        futureTask.cancel(true);
//        System.out.println("isCancelled: " + futureTask.isCancelled());

        System.out.printf("[%s]main: end%n", Thread.currentThread());
    }
}
