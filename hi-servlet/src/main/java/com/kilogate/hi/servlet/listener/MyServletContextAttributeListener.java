package com.kilogate.hi.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletContextAttributeListener
 *
 * @author kilogate
 * @create 2021/12/4 20:18
 **/
//@WebListener open this for test
public class MyServletContextAttributeListener implements ServletContextAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletContextAttributeListener.attributeAdded");
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletContextAttributeListener.attributeRemoved");
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletContextAttributeListener.attributeReplaced");
    }
}
