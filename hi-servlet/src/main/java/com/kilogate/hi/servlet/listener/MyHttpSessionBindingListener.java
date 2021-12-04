package com.kilogate.hi.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

/**
 * MyHttpSessionBindingListener
 *
 * @author kilogate
 * @create 2021/12/4 23:45
 **/
//@WebListener open this for test
public class MyHttpSessionBindingListener implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionBindingListener.valueBound");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionBindingListener.valueUnbound");
    }
}
