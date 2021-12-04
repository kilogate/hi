package com.kilogate.hi.servlet.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

/**
 * MyServletRequestAttributeListener
 *
 * @author kilogate
 * @create 2021/12/4 23:56
 **/
@WebListener
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAttributeListener.attributeAdded");
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAttributeListener.attributeRemoved");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAttributeListener.attributeReplaced");
    }
}
