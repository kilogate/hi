package com.kilogate.hi.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.leader.CancelLeadershipException;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CuratorUsage
 *
 * @author kilogate
 * @create 2021/12/17 16:55
 **/
public class CuratorUsage {
    public static void main(String[] args) throws Exception {
        test7();
    }

    // 创建会话、创建节点、删除节点、读取数据、更新数据
    private static void test1() throws Exception {
        // 1、创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 2、创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/p1/p2/p3/p4", "P1".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 创建节点 /p1/p2/p3/p4 完成 %n", new Date(), Thread.currentThread().getName());

        // 3、删除节点
        client.delete()
                .deletingChildrenIfNeeded()
                .forPath("/p1");
        System.out.printf("[%s] [%s] 删除节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        // 4、读取数据
        Stat stat = new Stat();
        byte[] data = client.getData().storingStatIn(stat).forPath("");
        System.out.printf("[%s] [%s] 读取数据完成, data: %s, stat: %s %n", new Date(), Thread.currentThread().getName(), new String(data), stat);

        // 5、更新数据
        Stat newStat = client.setData().withVersion(stat.getVersion()).forPath("", "NewCuratorUsage".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 更新数据完成, stat: %s %n", new Date(), Thread.currentThread().getName(), newStat);

        Thread.sleep(30000);
    }

    // 异步处理
    private static void test2() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 异步创建节点
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new MyBackgroundCallback(), "MyContext", executorService)
                .forPath("/p1/p2/p3/p4", "P1".getBytes(StandardCharsets.UTF_8));

        Thread.sleep(30000);
    }

    // 事件监听：节点监听
    private static void test3() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 节点监听
        NodeCache nodeCache = new NodeCache(client, "/p1");
        nodeCache.start();
        nodeCache.getListenable().addListener(new MyNodeCacheListener(nodeCache));
        System.out.printf("[%s] [%s] 监听节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/p1", "P1".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 创建节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 更新数据
        client.setData().forPath("/p1", "NewP1".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 更新节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 删除节点
        client.delete().forPath("/p1");
        System.out.printf("[%s] [%s] 删除节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(30000);
    }

    // 事件监听：子节点监听
    private static void test4() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 创建节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT) // 临时节点不能有子节点
                .forPath("/p1", "P1".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 创建节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 子节点监听
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/p1", true);
        pathChildrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        pathChildrenCache.getListenable().addListener(new MyPathChildrenCacheListener());
        System.out.printf("[%s] [%s] 监听子节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 创建子节点
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .forPath("/p1/p2", "P2".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 创建子节点 /p1/p2 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 更新节点（更新本身不会有通知）
        client.setData().forPath("/p1", "NewP1".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 更新节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 更新子节点
        client.setData().forPath("/p1/p2", "NewP2".getBytes(StandardCharsets.UTF_8));
        System.out.printf("[%s] [%s] 更新子节点 /p1/p2 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 删除子节点
        client.delete().forPath("/p1/p2");
        System.out.printf("[%s] [%s] 删除节点 /p1/p2 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(1000);

        // 删除节点（删除本身不会有通知）
        client.delete().forPath("/p1");
        System.out.printf("[%s] [%s] 删除节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(30000);
    }

    // Master选举
    private static void test5() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // Master选举
        String leaderPath = "/master" + System.currentTimeMillis() + "-";

        // 开启十个线程抢 Master
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LeaderSelector leaderSelector = new LeaderSelector(client, leaderPath, new LeaderSelectorListener() {
                    @Override
                    public void takeLeadership(CuratorFramework client) throws Exception {
                        System.out.printf("[%s] [%s] takeLeadership start %n", new Date(), Thread.currentThread().getName());
                        Thread.sleep(3000);
                        System.out.printf("[%s] [%s] takeLeadership end %n", new Date(), Thread.currentThread().getName());
                    }

                    @Override
                    public void stateChanged(CuratorFramework client, ConnectionState newState) {
                        if (ConnectionState.SUSPENDED == newState || ConnectionState.LOST == newState) {
                            throw new CancelLeadershipException();
                        }
                    }
                });

                leaderSelector.autoRequeue();
                leaderSelector.start();

                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(Integer.MAX_VALUE);
    }

    // 分布式锁
    private static void test6() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 分布式锁
        String lockPath = "/lock" + System.currentTimeMillis() + "-";
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);

        // 开启多个线程打印当前时间戳
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    // 获取分布式锁
                    lock.acquire();

                    // 等待所有线程都启动
                    countDownLatch.await();

                    // 打印当前时间戳
                    System.out.printf("[%s] [%s] 当前时间戳: %s %n", new Date(), Thread.currentThread().getName(), System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        // 释放分布式锁
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        // 开始执行
        countDownLatch.countDown();

        Thread.sleep(Integer.MAX_VALUE);
    }

    // 分布式计数器
    private static void test7() throws Exception {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .namespace("curator")
                .sessionTimeoutMs(50000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy)
                .build();

        // 启动会话
        client.start();
        System.out.printf("[%s] [%s] 创建会话完成 %n", new Date(), Thread.currentThread().getName());

        // 分布式计数器
        String counterPath = "/counter" + System.currentTimeMillis() + "-";
        DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(client, counterPath, new RetryNTimes(30, 100));

        // 开启多个线程累加计数器
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    AtomicValue<Integer> rc = atomicInteger.add(1);
                    System.out.printf("[%s] [%s] 累加完成, postValue: %s %n", new Date(), Thread.currentThread().getName(), rc.postValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        // 开始执行
        countDownLatch.countDown();

        Thread.sleep(Integer.MAX_VALUE);
    }

    private static class MyBackgroundCallback implements BackgroundCallback {
        @Override
        public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            System.out.printf("[%s] [%s] MyBackgroundCallback 收到事件通知: %s %n", new Date(), Thread.currentThread().getName(), event);
        }
    }

    private static class MyNodeCacheListener implements NodeCacheListener {
        private NodeCache nodeCache;

        public MyNodeCacheListener(NodeCache nodeCache) {
            this.nodeCache = nodeCache;
        }

        @Override
        public void nodeChanged() throws Exception {
            System.out.printf("[%s] [%s] MyNodeCacheListener 收到事件通知, currentData: %s %n",
                    new Date(), Thread.currentThread().getName(), nodeCache.getCurrentData());
        }
    }

    private static class MyPathChildrenCacheListener implements PathChildrenCacheListener {
        @Override
        public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
            System.out.printf("[%s] [%s] MyPathChildrenCacheListener 收到事件通知, event: %s %n",
                    new Date(), Thread.currentThread().getName(), event);
        }
    }
}
