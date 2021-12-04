package com.kilogate.hi.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * MyHttpSessionListener
 *
 * @author kilogate
 * @create 2021/12/4 23:34
 **/
//@WebListener open this for test
public class MyHttpSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionListener.sessionCreated");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionListener.sessionDestroyed");
    }
}
