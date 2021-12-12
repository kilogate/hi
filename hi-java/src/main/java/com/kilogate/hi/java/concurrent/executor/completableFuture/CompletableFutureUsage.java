package com.kilogate.hi.java.concurrent.executor.completableFuture;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
 * CompletableFuture<Void> thenAccept(Consumer<? super T> action)：对结果应用一个函数（无返回值）
 * CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)：对结果应用一个函数
 * CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)：对结果和错误应用一个函数（返回原结果）
 * CompletableFuture<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)：对结果和错误应用一个函数（返回函数结果）
 * CompletableFuture<U> thenCompose(Function<? super T, ? extends CompletionStage<U>> fn)：对结果应用一个函数（返回 future 并执行）
 * <p>
 * 组合多个 CompletableFuture 的方法
 * CompletableFuture<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)：执行两个动作并用给定函数组合结果
 * CompletableFuture<Void> thenAcceptBoth(CompletionStage<? extends U> other, BiConsumer<? super T, ? super U> action)：执行两个动作并用给定函数组合结果（无返回值）
 * CompletableFuture<Void> runAfterBoth(CompletionStage<?> other, Runnable action)：两个都完成后执行任务
 * CompletableFuture<Void> runAfterEither(CompletionStage<?> other,Runnable action)：其中一个完成后执行任务
 * CompletableFuture<Void> acceptEither(CompletionStage<? extends T> other, Consumer<? super T> action)：得到其中一个的结果时，传入给定的函数（无返回值）
 * CompletableFuture<U> applyToEither(CompletionStage<? extends T> other, Function<? super T, U> fn)：得到其中一个的结果时，传入给定的函数
 * static CompletableFuture<Void> allOf(CompletableFuture<?>... cfs)：所有给定的 future 都完成后完成
 * static CompletableFuture<Object> anyOf(CompletableFuture<?>... cfs)：任意给定的 future 完成后则完成
 *
 * @author kilogate
 * @create 2020/8/1 下午4:09
 **/
public class CompletableFutureUsage {
    public static void main(String[] args) {
        test14();
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

    // thenAccept
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
        CompletableFuture<Void> acceptFuture = taskFuture.thenAccept((taskResult) -> {
            System.out.printf("[%s] [%s] accept start, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] accept end, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
        });

        String taskResult = taskFuture.join();
        System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

        Void acceptResult = acceptFuture.join();
        System.out.printf("[%s] [%s] acceptResult: %s%n", new Date(), Thread.currentThread(), acceptResult);

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenApply
    private static void test5() {
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
            System.out.printf("[%s] [%s] apply start, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] apply end, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

            return "apply success";
        });

        String taskResult = taskFuture.join();
        System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

        String applyResult = applyFuture.join();
        System.out.printf("[%s] [%s] applyResult: %s%n", new Date(), Thread.currentThread(), applyResult);

        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // whenComplete
    private static void test6() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        // 正常执行的任务
//        Supplier<String> successTask = () -> {
//            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());
//
//            return "task success";
//        };

        // 会产生异常的任务
        Supplier<String> exceptionTask = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        Supplier<String> task = exceptionTask;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 组合可完成 Future
        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        CompletableFuture<String> whenCompleteFuture = taskFuture.whenComplete((taskResult, taskException) -> {
            System.out.printf("[%s] [%s] whenComplete start, taskResult: %s, taskException: %s%n", new Date(), Thread.currentThread(), taskResult, taskException);
            System.out.printf("[%s] [%s] whenComplete end, taskResult: %s, taskException: %s%n", new Date(), Thread.currentThread(), taskResult, taskException);
        });

        // 获取任务执行结果（join 方法必须 try catch）
        try {
            String taskResult = taskFuture.join();
            System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] taskException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 返回原结果（join 方法必须 try catch）
        try {
            String whenCompleteResult = whenCompleteFuture.join();
            System.out.printf("[%s] [%s] whenCompleteResult: %s%n", new Date(), Thread.currentThread(), whenCompleteResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] whenCompleteException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 关闭线程池
        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // handle
    private static void test7() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        // 正常执行的任务
        Supplier<String> successTask = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        // 会产生异常的任务
        Supplier<String> exceptionTask = () -> {
            System.out.printf("[%s] [%s] task start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            System.out.printf("[%s] [%s] task end%n", new Date(), Thread.currentThread());

            return "task success";
        };

        Supplier<String> task = exceptionTask;

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // 组合可完成 Future
        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        CompletableFuture<String> handleFuture = taskFuture.handle((taskResult, taskException) -> {
            System.out.printf("[%s] [%s] handle start, taskResult: %s, taskException: %s%n", new Date(), Thread.currentThread(), taskResult, taskException);
            System.out.printf("[%s] [%s] handle end, taskResult: %s, taskException: %s%n", new Date(), Thread.currentThread(), taskResult, taskException);
            return "handle success";
        });

        // 获取任务执行结果（join 方法必须 try catch）
        try {
            String taskResult = taskFuture.join();
            System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] taskException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 返回原结果（join 方法必须 try catch）
        try {
            String handleResult = handleFuture.join();
            System.out.printf("[%s] [%s] handleResult: %s%n", new Date(), Thread.currentThread(), handleResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] handleException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 关闭线程池
        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenCompose
    private static void test8() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        // 正常执行的任务
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

        // 组合可完成 Future
        CompletableFuture<String> taskFuture = CompletableFuture.supplyAsync(task, executorService);
        CompletableFuture<String> composeFuture = taskFuture.thenCompose(taskResult -> {
            System.out.printf("[%s] [%s] compose start, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] compose end, taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);

            return CompletableFuture.supplyAsync(() -> {
                System.out.printf("[%s] [%s] supply start%n", new Date(), Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("[%s] [%s] supply end%n", new Date(), Thread.currentThread());

                return "supply success";
            });
        });

        // 获取任务执行结果（join 方法必须 try catch）
        try {
            String taskResult = taskFuture.join();
            System.out.printf("[%s] [%s] taskResult: %s%n", new Date(), Thread.currentThread(), taskResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] taskException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 返回原结果（join 方法必须 try catch）
        try {
            String composeResult = composeFuture.join();
            System.out.printf("[%s] [%s] composeResult: %s%n", new Date(), Thread.currentThread(), composeResult);
        } catch (Exception e) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            System.out.printf("[%s] [%s] composeException: %s%n", new Date(), Thread.currentThread(), e);
        }

        // 关闭线程池
        executorService.shutdown();

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenCombine
    private static void test9() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Integer> future3 = future1.thenCombine(future2, (a, b) -> {
            System.out.printf("[%s] [%s] task3 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end%n", new Date(), Thread.currentThread());
            return a + b;
        });

        try {
            Integer task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // thenAcceptBoth
    private static void test10() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Void> future3 = future1.thenAcceptBoth(future2, (a, b) -> {
            System.out.printf("[%s] [%s] task3 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end%n", new Date(), Thread.currentThread());
        });

        try {
            Void task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // runAfterBoth
    private static void test11() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Void> future3 = future1.runAfterBoth(future2, () -> {
            System.out.printf("[%s] [%s] task3 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end%n", new Date(), Thread.currentThread());
        });

        try {
            Void task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // runAfterEither
    private static void test12() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Void> future3 = future1.runAfterEither(future2, () -> {
            System.out.printf("[%s] [%s] task3 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end%n", new Date(), Thread.currentThread());
        });

        try {
            Void task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // acceptEither
    private static void test13() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Void> future3 = future1.acceptEither(future2, (a) -> {
            System.out.printf("[%s] [%s] task3 start, a: %s%n", new Date(), Thread.currentThread(), a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end, a: %s%n", new Date(), Thread.currentThread(), a);
        });

        try {
            Void task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }

    // applyToEither
    private static void test14() {
        System.out.printf("[%s] [%s] test start%n", new Date(), Thread.currentThread());

        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task1 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task1 end%n", new Date(), Thread.currentThread());
            return 1;
        });

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.printf("[%s] [%s] task2 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task2 end%n", new Date(), Thread.currentThread());
            return 2;
        });

        CompletableFuture<Integer> future3 = future1.applyToEither(future2, (a) -> {
            System.out.printf("[%s] [%s] task3 start%n", new Date(), Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("[%s] [%s] task3 end%n", new Date(), Thread.currentThread());
            return a;
        });

        try {
            Integer task3Result = future3.get();
            System.out.printf("[%s] [%s] task3Result: %s%n", new Date(), Thread.currentThread(), task3Result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] [%s] test end%n", new Date(), Thread.currentThread());
    }
}
