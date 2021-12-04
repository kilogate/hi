package com.kilogate.hi.servlet.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletRequestListener
 *
 * @author kilogate
 * @create 2021/12/4 23:54
 **/
@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestListener.requestDestroyed");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestListener.requestInitialized");
    }
}
