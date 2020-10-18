package com.kilogate.hi.java.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 因特网地址的用法
 *
 * @author kilogate
 * @create 2020/10/18 16:19
 **/
public class InetAddressUsage {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName("time-a.nist.gov");
        System.out.println(inetAddress);

        InetAddress[] inetAddresses = InetAddress.getAllByName("baidu.com");
        System.out.println(Arrays.toString(inetAddresses));

        InetAddress localhost = InetAddress.getByName("localhost");
        System.out.println(localhost);

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);
        System.out.println(Arrays.toString(localHost.getAddress()));
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
    }
}
