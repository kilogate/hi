package com.kilogate.hi.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Date;
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
        test2();
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
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());

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
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());

        // 异步创建节点
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL)
                .inBackground(new MyBackgroundCallback(), "MyContext", executorService)
                .forPath("/p1/p2/p3/p4", "P1".getBytes(StandardCharsets.UTF_8));

        Thread.sleep(30000);
    }

    private static class MyBackgroundCallback implements BackgroundCallback {
        @Override
        public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
            System.out.printf("[%s] [%s] MyBackgroundCallback 收到事件通知: %s %n", new Date(), Thread.currentThread().getName(), event);
        }
    }
}
