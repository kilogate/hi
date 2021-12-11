package com.kilogate.hi.java.concurrent.executor.executorService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 执行器完成服务的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午4:11
 **/
public class ExecutorCompletionServiceUsage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test3();
    }

    /**
     * 任一任务执行完成就返回
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test1() throws ExecutionException, InterruptedException {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 3; i <= 13; i++) {
            int finalI = i;
            Callable<Integer> task = () -> {
                for (int j = 0; j < finalI; j++) {
                    System.out.println("task " + finalI + " is running: " + j);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("任务" + finalI + "被取消了，直接返回");
                        return 0;
                    }
                }
                return finalI;
            };

            tasks.add(task);
        }

        System.out.println("invokeAny start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        Integer result = executorService.invokeAny(tasks);
        System.out.println("invokeAny end");

        System.out.println("result: " + result);

        executorService.shutdown();
    }

    /**
     * 所有任务执行完成就返回
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test2() throws ExecutionException, InterruptedException {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 3; i <= 13; i++) {
            int finalI = i;
            Callable<Integer> task = () -> {
                for (int j = 0; j < finalI; j++) {
                    System.out.println("task " + finalI + " is running: " + j);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("任务" + finalI + "被取消了，直接返回");
                        return 0;
                    }
                }
                return finalI;
            };

            tasks.add(task);
        }

        System.out.println("invokeAll start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        System.out.println("invokeAll end");

        for (Future future : futures) {
            Object result = future.get();
            System.out.println("result: " + result);
        }

        executorService.shutdown();
    }

    /**
     * 完成一个返回一个
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void test3() throws ExecutionException, InterruptedException {
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 3; i <= 13; i++) {
            int finalI = i;
            Callable<Integer> task = () -> {
                for (int j = 0; j < finalI; j++) {
                    System.out.println("task " + finalI + " is running: " + j);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("任务" + finalI + "被取消了，直接返回");
                        return 0;
                    }
                }
                return finalI;
            };

            tasks.add(task);
        }

        System.out.println("submit start");
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService<>(executorService);

        for (Callable<Integer> task : tasks) {
            executorCompletionService.submit(task);
        }

        System.out.println("submit end");

        for (int i = 0; i < tasks.size(); i++) {
            Integer result = executorCompletionService.take().get();
            System.out.println(new Date() + " result: " + result);
        }

        executorService.shutdown();
    }
}
