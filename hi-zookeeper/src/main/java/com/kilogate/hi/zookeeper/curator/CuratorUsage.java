package com.kilogate.hi.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.Date;

/**
 * CuratorUsage
 *
 * @author kilogate
 * @create 2021/12/17 16:55
 **/
public class CuratorUsage {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    private static void test1() throws InterruptedException {
        // 1、创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/curator";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(
                connectString,
                5000,
                3000,
                retryPolicy);

        // 启动会话
        curatorFramework.start();
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(30000);
    }
}
