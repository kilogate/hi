package com.kilogate.hi.java.concurrent.producerAndConsumer;

import java.util.Date;
import java.util.Random;

/**
 * 生产者和消费者
 * <p>
 * 生产者和消费者问题是一个经典的线程同步问题。
 * 生产者（Producer）生产产品（Product），放入仓库（Repertory）；
 * 消费者（Consumer）消费产品，从仓库里获取。
 * 仓库爆满时生产者等待消费者消费，仓库为空时消费者等待生产者生产。
 *
 * @author kilogate
 * @create 2020/8/9 下午9:28
 **/
public class ProducerAndConsumer {
    public static void main(String[] args) {
        Repository repository = new Repository2();

        Runnable producer = () -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String product = "产品" + i;
                boolean produce = repository.produce(product);

                if (produce) {
                    System.out.println(String.format("%s [%s] 生产成功: %s", new Date(), Thread.currentThread(), product));
                } else {
                    System.out.println(String.format("%s [%s] 生产失败: %s", new Date(), Thread.currentThread(), product));
                }
            }
        };

        Runnable consumer = () -> {
            for (int i = 0; i < 12; i++) {
                Object product = repository.consume();

                if (product != null) {
                    System.out.println(String.format("%s [%s] 消费成功: %s", new Date(), Thread.currentThread(), product));
                } else {
                    System.out.println(String.format("%s [%s] 消费失败: %s", new Date(), Thread.currentThread(), product));
                }

                try {
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(producer).start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(consumer).start();
    }
}
