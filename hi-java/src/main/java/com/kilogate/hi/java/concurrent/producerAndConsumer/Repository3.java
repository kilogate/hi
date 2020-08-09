package com.kilogate.hi.java.concurrent.producerAndConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * 仓库三：使用 BlockingDeque 实现
 *
 * @author kilogate
 * @create 2020/8/9 下午10:32
 **/
public class Repository3 implements Repository {
    private BlockingQueue<Object> list = new LinkedBlockingDeque<>(MAX_SIZE);

    @Override
    public boolean produce(Object product) {
        try {
            return list.offer(product, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object consume() {
        try {
            return list.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
