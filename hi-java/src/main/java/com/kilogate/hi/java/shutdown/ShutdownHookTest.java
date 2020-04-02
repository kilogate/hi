package com.kilogate.hi.java.shutdown;

/**
 * ShutdownHookTest
 *
 * @author fengquanwei
 * @create 2020/4/2 下午11:44
 **/
public class ShutdownHookTest {
    public static void main(String[] args) {
        // 工作线程
        Thread work = new Thread(() -> {
            System.out.println("work start");

            int i = 0;
            while (i < 10) {
                System.out.println("working: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
            }

            System.out.println("work end");
        });

        // 1 启动工作线程
        work.start();

        // 钩子线程
        Thread hook = new Thread(() -> {
            System.out.println("hooking start");

            // 等工作线程执行完再退出，最多等5s
            int wait = 0;
            while (work.getState() != Thread.State.TERMINATED && wait < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                wait++;
            }

            System.out.println("hooking end");
        });

        // 2 添加钩子线程
        Runtime.getRuntime().addShutdownHook(hook);
    }
}
