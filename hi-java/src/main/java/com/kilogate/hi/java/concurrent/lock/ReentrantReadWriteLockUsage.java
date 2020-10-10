package com.kilogate.hi.java.concurrent.lock;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的用法
 *
 * @author kilogate
 * @create 2020/8/1 下午3:47
 **/
public class ReentrantReadWriteLockUsage {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable readTask = () -> {
            lock.readLock().lock();

            try {
                System.out.println(String.format("%s [%s] read start", new Date(), Thread.currentThread()));
                Thread.sleep(new Random().nextInt(3000));
                System.out.println(String.format("%s [%s] read end", new Date(), Thread.currentThread()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        };

        Runnable writeTask = () -> {
            lock.writeLock().lock();

            try {
                System.out.println(String.format("%s [%s] write start", new Date(), Thread.currentThread()));
                Thread.sleep(new Random().nextInt(3000));
                System.out.println(String.format("%s [%s] write end", new Date(), Thread.currentThread()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.writeLock().unlock();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(readTask).start();
            new Thread(writeTask).start();
        }
    }
}
