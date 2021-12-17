package com.kilogate.hi.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;

import java.util.Date;

/**
 * ZkClientUsage
 *
 * @author kilogate
 * @create 2021/12/17 16:06
 **/
public class ZkClientUsage {
    public static void main(String[] args) throws InterruptedException {
        test1();
    }

    // 创建会话、创建和删除节点
    private static void test1() throws InterruptedException {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/zkClient";
        ZkClient zkClient = new ZkClient(connectString);
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());

        // 递归创建父节点
        zkClient.createPersistent("/p1/p2/p3/p4", true);
        System.out.printf("[%s] [%s] 递归创建父节点完成 %n", new Date(), Thread.currentThread().getName());

        Thread.sleep(3000);

        // 递归删除节点
        boolean success = zkClient.deleteRecursive("/p1");
        System.out.printf("[%s] [%s] 递归删除节点完成, success: %s %n", new Date(), Thread.currentThread().getName(), success);
    }
}
