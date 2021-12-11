package com.kilogate.hi.java.concurrent.executor.executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 执行器服务的用法
 * invokeAny
 * invokeAll
 *
 * @author kilogate
 * @create 2020/8/1 下午4:10
 **/
public class ExecutorServiceUsage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("start");

        ExecutorService executorService = Executors.newCachedThreadPool();

        List<Future> futureList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = executorService.submit(() -> {
                try {
                    Thread.sleep(new Random().nextInt(3) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "success" + i);

            futureList.add(future);
        }

        for (Future future : futureList) {
            Object result = future.get();
            System.out.println(result);
        }

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        System.out.println("getLargestPoolSize: " + threadPoolExecutor.getLargestPoolSize());

        executorService.shutdown();

        System.out.println("end");
    }
}
