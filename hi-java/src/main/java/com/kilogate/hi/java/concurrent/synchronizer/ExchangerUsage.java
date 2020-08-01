package com.kilogate.hi.java.concurrent.synchronizer;

import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * 交换器的使用
 *
 * @author fengquanwei
 * @create 2020/7/30 上午12:06
 **/
public class ExchangerUsage {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();

        Runnable task = () -> {
            System.out.println(String.format("%s start", Thread.currentThread()));

            try {
                Thread.sleep(new Random().nextInt(10000));

                String myData = Thread.currentThread().toString();
                String exchangeData = exchanger.exchange(myData);

                System.out.println(String.format("%s myData: %s, exchange: %s", Thread.currentThread(), myData, exchangeData));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("%s end", Thread.currentThread()));
        };

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
