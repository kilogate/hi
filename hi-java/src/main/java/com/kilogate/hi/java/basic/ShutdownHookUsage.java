package com.kilogate.hi.java.basic;

import java.util.ArrayList;

/**
 * 关闭钩子的用法
 *
 * @author kilogate
 * @create 2020/8/8 上午10:21
 **/
public class ShutdownHookUsage {
    public static void main(String[] args) {
        // add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("do sth. in shutdown hook: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));

        System.out.println(String.format("%s start", Thread.currentThread()));

        for (int i = 0; i < 3; i++) {
            System.out.println("working " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // NPE
        new ArrayList<String>().get(0);

        System.out.println(String.format("%s end", Thread.currentThread()));
    }
}
