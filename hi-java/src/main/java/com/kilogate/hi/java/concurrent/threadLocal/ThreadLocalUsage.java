package com.kilogate.hi.java.concurrent.threadLocal;

import java.util.Random;

/**
 * 线程局部变量的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午3:45
 **/
public class ThreadLocalUsage {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        // 当前线程
        threadLocal.set(Thread.currentThread().getName());
        System.out.println(String.format("线程: %s, 值: %s", Thread.currentThread(), threadLocal.get()));

        // 新建线程
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadLocal.set(Thread.currentThread().getName());
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("线程: %s, 值: %s", Thread.currentThread(), threadLocal.get()));
            }).start();
        }
    }
}
