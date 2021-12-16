package com.kilogate.hi.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * ClientUsage
 *
 * @author kilogate
 * @create 2021/12/16 21:38
 **/
public class ClientUsage {
    private static class MyWatcher implements Watcher {
        private CountDownLatch countDownLatch;

        public MyWatcher(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.printf("[%s] [%s] 收到监听事件: %s %n", new Date(), Thread.currentThread().getName(), watchedEvent);

            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        // 创建会话
        System.out.printf("[%s] [%s] 开始创建会话 %n", new Date(), Thread.currentThread().getName());
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/client";
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, new MyWatcher(countDownLatch));

        // 等待会话创建成功
        countDownLatch.await();
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());
    }
}
