package com.kilogate.hi.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * MyHttpSessionActivationListener
 *
 * @author kilogate
 * @create 2021/12/4 23:42
 **/
//@WebListener open this for test
public class MyHttpSessionActivationListener implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionActivationListener.sessionWillPassivate");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionActivationListener.sessionDidActivate");
    }
}
