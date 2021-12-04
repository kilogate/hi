package com.kilogate.hi.servlet.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * MyHttpSessionAttributeListener
 *
 * @author kilogate
 * @create 2021/12/4 23:40
 **/
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionAttributeListener.attributeAdded");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionAttributeListener.attributeRemoved");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("com.kilogate.hi.servlet.listener.MyHttpSessionAttributeListener.attributeReplaced");
    }
}
