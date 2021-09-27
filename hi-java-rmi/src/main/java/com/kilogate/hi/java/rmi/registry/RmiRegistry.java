package com.kilogate.hi.java.rmi.registry;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;

/**
 * Registry
 *
 * @author kilogate
 * @create 2021/9/27 15:10
 **/
public class RmiRegistry {
    public static void main(String[] args) throws IOException {
        // 第一步：启动 Registry
        LocateRegistry.createRegistry(1234);

        System.out.println("registry ready");

        // 通过等待输入实现 main 线程不退出
        System.in.read();

        System.out.println("registry exit");
    }
}
