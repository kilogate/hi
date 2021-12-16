package com.kilogate.hi.zookeeper.client;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * ClientUsage
 *
 * @author kilogate
 * @create 2021/12/16 21:38
 **/
public class ClientUsage {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        test4();
    }

    // 创建会话
    private static void test1() throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyWatcher myWatcher = new MyWatcher(countDownLatch);

        // 创建会话
        System.out.printf("[%s] [%s] 开始创建会话 %n", new Date(), Thread.currentThread().getName());
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/client";
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, myWatcher);

        // 等待会话创建成功
        countDownLatch.await();
        System.out.printf("[%s] [%s] 完成创建会话, 会话ID:%s %n", new Date(), Thread.currentThread().getName(), zooKeeper.getSessionId());
    }

    // 复用会话
    private static void test2() throws InterruptedException, IOException {
        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        MyWatcher myWatcher1 = new MyWatcher(countDownLatch1);

        // 创建会话
        System.out.printf("[%s] [%s] 开始创建会话 %n", new Date(), Thread.currentThread().getName());
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/client";
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, myWatcher1);

        // 等待会话创建成功
        countDownLatch1.await();
        System.out.printf("[%s] [%s] 完成创建会话, 会话ID:%s %n", new Date(), Thread.currentThread().getName(), zooKeeper.getSessionId());

        // 复用会话
        CountDownLatch countDownLatch2 = new CountDownLatch(1);
        MyWatcher myWatcher2 = new MyWatcher(countDownLatch2);
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        zooKeeper = new ZooKeeper(connectString, 5000, myWatcher2, sessionId, sessionPasswd);

        // 等待会话复用成功
        countDownLatch2.await();
        System.out.printf("[%s] [%s] 完成复用会话, 会话ID:%s %n", new Date(), Thread.currentThread().getName(), zooKeeper.getSessionId());
    }

    // 创建节点
    private static void test3() throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyWatcher myWatcher = new MyWatcher(countDownLatch);

        // 创建会话
        System.out.printf("[%s] [%s] 开始创建会话 %n", new Date(), Thread.currentThread().getName());
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/client";
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, myWatcher);

        // 等待会话创建成功
        countDownLatch.await();
        System.out.printf("[%s] [%s] 完成创建会话, 会话ID:%s %n", new Date(), Thread.currentThread().getName(), zooKeeper.getSessionId());

        // 创建节点

        // 同步创建临时节点
        String path1 = zooKeeper.create("/path1", "PATH1".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.printf("[%s] [%s] 同步创建临时节点完成:%s %n", new Date(), Thread.currentThread().getName(), path1);

        // 同步创建临时顺序节点
        String path2 = zooKeeper.create("/path2", "PATH2".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.printf("[%s] [%s] 同步创建临时顺序节点完成:%s %n", new Date(), Thread.currentThread().getName(), path2);

        // 异步创建临时节点
        MyStringCallback myStringCallback = new MyStringCallback();
        zooKeeper.create("/path3", "PATH3".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, myStringCallback, "MyContext");

        // 异步创建临时顺序节点
        zooKeeper.create("/path4", "PATH4".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, myStringCallback, "MyContext");

        Thread.sleep(1000000);
    }

    // 删除节点
    private static void test4() throws IOException, InterruptedException, KeeperException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        MyWatcher myWatcher = new MyWatcher(countDownLatch);

        // 创建会话
        System.out.printf("[%s] [%s] 开始创建会话 %n", new Date(), Thread.currentThread().getName());
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/client";
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 5000, myWatcher);

        // 等待会话创建成功
        countDownLatch.await();
        System.out.printf("[%s] [%s] 完成创建会话, 会话ID:%s %n", new Date(), Thread.currentThread().getName(), zooKeeper.getSessionId());

        // 同步创建持久节点
        String path1 = zooKeeper.create("/path1", "PATH1".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.printf("[%s] [%s] 同步创建持久节点完成:%s %n", new Date(), Thread.currentThread().getName(), path1);

        // 同步创建持久顺序节点
        String path2 = zooKeeper.create("/path2", "PATH2".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.printf("[%s] [%s] 同步创建持久顺序节点完成:%s %n", new Date(), Thread.currentThread().getName(), path2);

        Thread.sleep(5000);

        // 同步删除节点
        zooKeeper.delete(path1, -1);
        System.out.printf("[%s] [%s] 同步删除节点完成:%s %n", new Date(), Thread.currentThread().getName(), path1);

        // 异步删除节点
        zooKeeper.delete(path2, -1, new MyVoidCallback(), "MyContext");

        Thread.sleep(1000000);
    }

    private static class MyWatcher implements Watcher {
        private CountDownLatch countDownLatch;

        public MyWatcher(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void process(WatchedEvent watchedEvent) {
            System.out.printf("[%s] [%s] MyWatcher 收到事件通知: %s %n", new Date(), Thread.currentThread().getName(), watchedEvent);

            if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                countDownLatch.countDown();
            }
        }
    }

    private static class MyStringCallback implements AsyncCallback.StringCallback {
        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            System.out.printf("[%s] [%s] MyStringCallback收到事件通知, rc: %s, path: %s, ctx: %s, name: %s %n",
                    new Date(), Thread.currentThread().getName(), rc, path, ctx, name);
        }
    }

    private static class MyVoidCallback implements AsyncCallback.VoidCallback {
        @Override
        public void processResult(int rc, String path, Object ctx) {
            System.out.printf("[%s] [%s] MyVoidCallback收到事件通知, rc: %s, path: %s, ctx: %s %n",
                    new Date(), Thread.currentThread().getName(), rc, path, ctx);
        }
    }
}
