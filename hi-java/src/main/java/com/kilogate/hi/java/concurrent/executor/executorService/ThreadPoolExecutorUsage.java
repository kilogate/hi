package com.kilogate.hi.java.concurrent.executor.executorService;

import java.util.concurrent.*;

/**
 * ThreadPoolExecutorUsage
 *
 * @author fengquanwei
 * @create 2022/4/23 17:38
 **/
public class ThreadPoolExecutorUsage {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("main start");

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 100, 0L, TimeUnit.SECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            // 方法一：i = 0 时没有打印异常堆栈，不推荐使用
//            threadPoolExecutor.submit(new DivTask(10, i));
            // 方法二：有异常堆栈：任务线程堆栈
//            threadPoolExecutor.execute(new DivTask(10, i));
            // 方法三：有异常堆栈：主线程堆栈 + 任务线程堆栈
            Future<?> future = threadPoolExecutor.submit(new DivTask(10, i));
            future.get(); // 此处仅为示例，不能放在此处哈，否则就是同步执行了
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1, TimeUnit.HOURS);

        System.out.println("main end");
    }

    private static class DivTask implements Runnable {
        private int a;
        private int b;

        public DivTask(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            System.out.printf("run start, a: %s, b: %s%n", a, b);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int c = a / b;
            System.out.printf("run end, a: %s, b: %s, c: %s%n", a, b, c);
        }
    }
}
