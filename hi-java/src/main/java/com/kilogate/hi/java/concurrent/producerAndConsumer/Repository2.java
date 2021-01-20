package com.kilogate.hi.java.concurrent.producerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 仓库二：使用 Lock 和 Condition 实现
 *
 * @author kilogate
 * @create 2020/8/9 下午10:18
 **/
public class Repository2 implements Repository {
    private LinkedList<Object> list = new LinkedList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition emptyCondition = lock.newCondition();
    private final Condition fullCondition = lock.newCondition();

    @Override
    public boolean produce(Object product) {
        lock.lock();

        try {
            return doProduce(product);
        } finally {
            lock.unlock();
        }
    }

    private boolean doProduce(Object product) {
        // 仓库已满，等待消费者消费
        boolean retry = false;
        while (list.size() == MAX_SIZE) {
            if (retry) {
                System.out.println(String.format("[%s] 生产失败: %s，等待超时, 退出生产", Thread.currentThread(), product));
                return false;
            }

            try {
                System.out.println((String.format("[%s] 生产失败: %s, 仓库已满，等待消费", Thread.currentThread(), product)));
                emptyCondition.await(10, TimeUnit.SECONDS);

                retry = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println((String.format("[%s] 生产失败: %s，发生异常: %s, 退出生产", Thread.currentThread(), product, e.getMessage())));
                return false;
            }
        }

        // 产品入库
        list.add(product);
        System.out.println((String.format("[%s] 生产成功: %s, 产品库存: %s", Thread.currentThread(), product, list.size())));

        // 通知所有消费者
        emptyCondition.signalAll();

        return true;
    }

    @Override
    public Object consume() {
        lock.lock();

        try {
            return doConsume();
        } finally {
            lock.unlock();
        }
    }

    private Object doConsume() {
        // 仓库为空，等待生产者生产
        boolean retry = false;
        while (list.size() == 0) {
            if (retry) {
                System.out.println((String.format("[%s] 消费失败, 等待超时，退出消费", Thread.currentThread())));
                return null;
            }

            try {
                System.out.println((String.format("[%s] 消费失败, 仓库为空，等待生产", Thread.currentThread())));
                fullCondition.await(10, TimeUnit.SECONDS);

                retry = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println((String.format("[%s] 消费失败，发生异常: %s, 退出消费", Thread.currentThread(), e.getMessage())));
                return null;
            }
        }

        // 产品出库
        Object product = list.remove();
        System.out.println((String.format("[%s] 消费成功: %s, 产品库存: %s", Thread.currentThread(), product, list.size())));

        // 通知所有生产者
        fullCondition.signalAll();

        return product;
    }
}
