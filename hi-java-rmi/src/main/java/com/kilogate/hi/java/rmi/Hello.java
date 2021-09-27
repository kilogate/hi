package com.kilogate.hi.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Hello
 *
 * @author kilogate
 * @create 2021/9/27 15:07
 **/
public interface Hello extends Remote {
    String hello(String name) throws RemoteException;
}
