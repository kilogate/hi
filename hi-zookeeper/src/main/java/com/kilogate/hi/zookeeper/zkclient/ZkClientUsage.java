package com.kilogate.hi.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.util.Date;
import java.util.List;

/**
 * ZkClientUsage
 *
 * @author kilogate
 * @create 2021/12/17 16:06
 **/
public class ZkClientUsage {
    public static void main(String[] args) throws InterruptedException {
        test2();
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

    // 获取子节点列表、监听子节点变更
    private static void test2() throws InterruptedException {
        // 创建会话
        String connectString = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183/zkClient";
        ZkClient zkClient = new ZkClient(connectString);
        System.out.printf("[%s] [%s] 完成创建会话 %n", new Date(), Thread.currentThread().getName());

        // 创建子节点
        zkClient.createEphemeral("/p1", "P1");
        System.out.printf("[%s] [%s] 创建子节点 /p1 完成 %n", new Date(), Thread.currentThread().getName());
        zkClient.createEphemeral("/p2", "P2");
        System.out.printf("[%s] [%s] 创建子节点 /p2 完成 %n", new Date(), Thread.currentThread().getName());
        zkClient.createEphemeral("/p3", "P3");
        System.out.printf("[%s] [%s] 创建子节点 /p3 完成 %n", new Date(), Thread.currentThread().getName());

        // 1、获取子节点列表
        List<String> children = zkClient.getChildren("/");
        System.out.printf("[%s] [%s] 获取子节点列表完成, children: %s %n", new Date(), Thread.currentThread().getName(), children);

        // 2、监听子节点变更
        zkClient.subscribeChildChanges("/", new MyZkChildListener());

        // 创建子节点
        zkClient.createEphemeral("/p4", "P4");
        System.out.printf("[%s] [%s] 创建子节点 /p4 完成 %n", new Date(), Thread.currentThread().getName());

        // 删除子节点
        boolean success = zkClient.delete("/p4");
        System.out.printf("[%s] [%s] 删除子节点 /p4 完成, success: %s %n", new Date(), Thread.currentThread().getName(), success);

        Thread.sleep(30000);
    }

    private static class MyZkChildListener implements IZkChildListener {
        @Override
        public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
            System.out.printf("[%s] [%s] MyZkChildListener收到事件通知, parentPath: %s, currentChilds: %s %n",
                    new Date(), Thread.currentThread().getName(), parentPath, currentChilds);
        }
    }
}
