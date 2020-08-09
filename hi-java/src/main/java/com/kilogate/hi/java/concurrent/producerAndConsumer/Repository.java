package com.kilogate.hi.java.concurrent.producerAndConsumer;

/**
 * 仓库
 *
 * @author kilogate
 * @create 2020/8/9 下午9:32
 **/
public interface Repository {
    /**
     * 最大容量
     */
    int MAX_SIZE = 5;

    /**
     * 生产产品
     *
     * @param product
     */
    boolean produce(Object product);

    /**
     * 消费产品
     *
     * @return
     */
    Object consume();
}
