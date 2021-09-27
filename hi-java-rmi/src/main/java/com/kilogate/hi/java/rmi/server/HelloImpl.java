package com.kilogate.hi.java.rmi.server;

import com.kilogate.hi.java.rmi.Hello;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * HelloImpl
 *
 * @author kilogate
 * @create 2021/9/27 15:08
 **/
public class HelloImpl extends UnicastRemoteObject implements Hello {
    protected HelloImpl() throws RemoteException {
        super();
    }

    @Override
    public String hello(String name) throws RemoteException {
        return "Hi, " + name;
    }
}
