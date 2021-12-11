package com.kilogate.hi.java.concurrent.executor.executorService;

import java.util.Date;
import java.util.concurrent.*;

/**
 * 预定执行服务的用法
 * schedule
 * scheduleAtFixedRate
 * scheduleWithFixedDelay
 *
 * @author kilogate
 * @create 2020/8/1 下午4:12
 **/
public class ScheduledExecutorServiceUsage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test3();
    }

    private static void test1() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        // 预定在指定的时间之后执行任务
        ScheduledFuture<String> scheduledFuture = scheduledExecutorService.schedule(() -> {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread() + ": " + new Date());
            return "success";
        }, 1, TimeUnit.SECONDS);

        String result = scheduledFuture.get();
        System.out.println(result);

        scheduledExecutorService.shutdown();
    }

    private static void test2() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        ScheduledFuture<?> scheduleAtFixedRate = scheduledExecutorService.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + ": " + new Date());
        }, 1, 1, TimeUnit.SECONDS);

        // 不会停，永久阻塞
//        Object result = scheduleAtFixedRate.get();
//        System.out.println(result);

        Thread.sleep(10000);

        scheduledExecutorService.shutdown();
    }

    private static void test3() throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

        ScheduledFuture<?> scheduleAtFixedRate = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + ": " + new Date());
        }, 1, 1, TimeUnit.SECONDS);

        // 不会停，永久阻塞
//        Object result = scheduleAtFixedRate.get();
//        System.out.println(result);

        Thread.sleep(10000);

        scheduledExecutorService.shutdown();
    }
}
