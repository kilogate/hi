package com.kilogate.hi.java.concurrent.producerAndConsumer;

import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * 仓库一：使用 synchronized wait notifyAll 实现
 *
 * @author kilogate
 * @create 2020/8/9 下午9:31
 **/
public class Repository1 implements Repository {
    private static final Logger logger = Logger.getLogger(Repository1.class.getName());
    private LinkedList<Object> list = new LinkedList<>();

    @Override
    public synchronized void produce(Object product) {
        // 仓库已满，等待消费者消费
        int retry = 0;
        while (list.size() == MAX_SIZE) {
            if (retry >= 3) {
                logger.info(String.format("[%s] 生产失败, 超过等待次数: %s，退出生产", Thread.currentThread(), retry++));
                return;
            }

            try {
                logger.info(String.format("[%s] 生产失败, 仓库已满，等待消费", Thread.currentThread()));
                wait(10000);
                retry++;
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.severe(String.format("[%s] 生产失败，发生异常: %s", Thread.currentThread(), e.getMessage()));
                return;
            }
        }

        // 产品入库
        list.add(product);
        logger.info(String.format("[%s] 生产成功: %s, 产品库存: %s", Thread.currentThread(), product, list.size()));

        // 通知所有消费者
        notifyAll();
    }

    @Override
    public synchronized Object consume() {
        // 仓库为空，等待生产者生产
        int retry = 0;
        while (list.size() == 0) {
            if (retry >= 3) {
                logger.info(String.format("[%s] 消费失败, 超过等待次数: %s，退出消费", Thread.currentThread(), retry++));
                return null;
            }

            try {
                logger.info(String.format("[%s] 消费失败, 仓库为空，等待生产", Thread.currentThread()));
                wait(10000);
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
        notifyAll();

        return product;
    }
}
