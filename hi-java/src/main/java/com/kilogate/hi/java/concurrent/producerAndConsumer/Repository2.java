package com.kilogate.hi.java.concurrent.producerAndConsumer;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * 仓库二：使用 Lock 和 Condition 实现
 *
 * @author fengquanwei
 * @create 2020/8/9 下午10:18
 **/
public class Repository2 implements Repository {
    private static final Logger logger = Logger.getLogger(Repository2.class.getName());

    private LinkedList<Object> list = new LinkedList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition emptyCondition = lock.newCondition();
    private final Condition fullCondition = lock.newCondition();

    @Override
    public void produce(Object product) {
        lock.lock();

        try {
            doProduce(product);
        } finally {
            lock.unlock();
        }
    }

    private void doProduce(Object product) {
        // 仓库已满，等待消费者消费
        int retry = 0;
        while (list.size() == MAX_SIZE) {
            if (retry >= 3) {
                logger.info(String.format("[%s] 生产失败: %s, 超过等待次数: %s，退出生产", Thread.currentThread(), product, retry++));
                return;
            }

            try {
                logger.info(String.format("[%s] 生产失败: %s, 仓库已满，等待消费", Thread.currentThread(), product));
                emptyCondition.await(10, TimeUnit.SECONDS);
                retry++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.severe(String.format("[%s] 生产失败: %s，发生异常: %s", Thread.currentThread(), product, e.getMessage()));
                return;
            }
        }

        // 产品入库
        list.add(product);
        logger.info(String.format("[%s] 生产成功: %s, 产品库存: %s", Thread.currentThread(), product, list.size()));

        // 通知所有消费者
        emptyCondition.signalAll();
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
        int retry = 0;
        while (list.size() == 0) {
            if (retry >= 3) {
                logger.info(String.format("[%s] 消费失败, 超过等待次数: %s，退出消费", Thread.currentThread(), retry++));
                return null;
            }

            try {
                logger.info(String.format("[%s] 消费失败, 仓库为空，等待生产", Thread.currentThread()));
                fullCondition.await(10, TimeUnit.SECONDS);
                retry++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.severe(String.format("[%s] 消费失败，发生异常: %s", Thread.currentThread(), e.getMessage()));
                return null;
            }
        }

        // 产品出库
        Object product = list.remove();
        logger.info(String.format("[%s] 消费成功: %s, 产品库存: %s", Thread.currentThread(), product, list.size()));

        // 通知所有生产者
        fullCondition.signalAll();

        return product;
    }
}
