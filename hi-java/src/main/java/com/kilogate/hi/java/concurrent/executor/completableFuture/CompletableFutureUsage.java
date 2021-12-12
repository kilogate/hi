package com.kilogate.hi.java.concurrent.executor.completableFuture;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

/**
 * 可完成 Future 的用法
 * <p>
 * 创建一个 CompletableFuture 的方法
 * static CompletableFuture<Void> runAsync(Runnable runnable)
 * static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * <p>
 * 处理单个 CompletableFuture 的方法
 * CompletableFuture<Void> thenRun(Runnable action)：执行一个任务
 * CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)：对结果应用一个函数
 * CompletableFuture<Void> thenAccept(Consumer<? super T> action)：对结果应用一个函数（无返回值）
 * CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)：对结果应用一个函数（返回 future 并执行）
 * CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)：对结果和错误应用一个函数（返回函数结果）
 * CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)：对结果和错误应用一个函数（返回原结果）
 * <p>
 * 组合多个 CompletableFuture 的方法
 * CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)：执行两个动作并用给定函数组合结果
 * CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action)：执行两个动作并用给定函数组合结果（无返回值）
 * CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action)：两个都完成后执行任务
 * CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action)：其中一个完成后执行任务
 * CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)：得到其中一个的结果时，传入给定的函数
 * CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)：得到其中一个的结果时，传入给定的函数（无返回值）
 * static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)：所有给定的 future 都完成后完成
 * static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)：任意给定的 future 完成后则完成
 *
 * @author kilogate
 * @create 2020/8/1 下午4:09
 **/
public class CompletableFutureUsage {
    public static void main(String[] args) {
        test4();
    }

    // runAsync
    private static void test1() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        Runnable task = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> taskFuture = CompletableFuture.runAsync(task, executorService);
        taskFuture.join();

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // supplyAsync
    private static void test2() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        Supplier<String> task = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        String taskResult = taskFuture.join();
        System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenRun
    private static void test3() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        Supplier<String> task = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        CompletableFuture<Void> runFuture = taskFuture.thenRun(() -> {
            System.out.printf("[%s] [%s] run start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] run end%n", new Date(), Thread.currentThread());
        });

        String taskResult = taskFuture.join();
        System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

        Void runResult = runFuture.join();
        System.out.printf("[%s] [%s] runResult: %s%n", new Date(), Thread.currentThread(), runResult);

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenApply
    private static void test4() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        Supplier<String> task = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        CompletableFuture<String> applyFuture = taskFuture.thenApply((taskResult) -> {
            System.out.printf("[%s] [%s] apply start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] apply end%n", new Date(), Thread.currentThread());

            return "apply success, and taskResult: " + taskResult;
        });

        String taskResult = taskFuture.join();
        System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

        String applyResult = applyFuture.join();
        System.out.printf("[%s] [%s] applyResult: %s%n", new Date(), Thread.currentThread(), applyResult);

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }
}
