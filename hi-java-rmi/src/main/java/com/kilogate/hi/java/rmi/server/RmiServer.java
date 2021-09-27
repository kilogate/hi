package com.kilogate.hi.java.rmi.server;

import com.kilogate.hi.java.rmi.Hello;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Server
 *
 * @author kilogate
 * @create 2021/9/27 15:09
 **/
public class RmiServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        Hello hello = new HelloImpl();

        // 第二步：绑定服务
        Naming.bind("rmi://127.0.0.1:1234/hello", hello);

        System.out.println("server ready");
    }
}
