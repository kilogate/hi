package com.kilogate.hi.java.rmi.client;

import com.kilogate.hi.java.rmi.Hello;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * RmiClient
 *
 * @author kilogate
 * @create 2021/9/27 15:13
 **/
public class RmiClient {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        // 第三步：远程调用对象
        Hello hello = (Hello) Naming.lookup("rmi://localhost:1234/hello");
        String tom = hello.hello("Tom");
        System.out.println(tom);
    }
}
