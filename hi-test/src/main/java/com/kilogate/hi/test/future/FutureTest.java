package com.kilogate.hi.test.future;

import java.util.concurrent.*;

/**
 * FutureTest
 *
 * @author kilogate
 * @create 2021/12/6 17:31
 **/
public class FutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<?> future = executorService.submit(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(10000000);
            System.out.println(Thread.currentThread().getName() + " end");
            return "success";
        });

        Object result = future.get(10000000, TimeUnit.MILLISECONDS);
        System.out.println(result);
    }
}
