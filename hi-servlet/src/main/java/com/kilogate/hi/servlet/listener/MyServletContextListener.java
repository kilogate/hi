package com.kilogate.hi.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletContextListener
 *
 * @author kilogate
 * @create 2021/12/4 20:16
 **/
//@WebListener open this for test
public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletContextListener.contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletContextListener.contextDestroyed");
    }
}
