package com.kilogate.hi.java.rmi.registry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Registry
 *
 * @author kilogate
 * @create 2021/9/27 15:10
 **/
public class RmiRegistry {
    public static void main(String[] args) throws RemoteException {
        // 第一步：启动 Registry
        LocateRegistry.createRegistry(1234);

        System.out.println("registry ready");
    }
}
