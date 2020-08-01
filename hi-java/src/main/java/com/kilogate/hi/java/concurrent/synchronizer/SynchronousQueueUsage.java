package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.UUID;
import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列的使用
 *
 * @author fengquanwei
 * @create 2020/7/30 上午12:07
 **/
public class SynchronousQueueUsage {
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        Runnable producer = () -> {
            System.out.println(String.format("%s start", Thread.currentThread()));

            String data = UUID.randomUUID().toString();
            try {
                System.out.println(String.format("%s put: %s", Thread.currentThread(), data));
                synchronousQueue.put(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s end", Thread.currentThread()));
        };

        Runnable consumer = () -> {
            System.out.println(String.format("%s start", Thread.currentThread()));

            try {
                String data = synchronousQueue.take();

                System.out.println(String.format("%s take: %s", Thread.currentThread(), data));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s end", Thread.currentThread()));
        };

        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
