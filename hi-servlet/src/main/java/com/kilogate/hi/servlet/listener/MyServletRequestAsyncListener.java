package com.kilogate.hi.servlet.listener;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyServletRequestAsyncListener
 *
 * @author kilogate
 * @create 2021/12/4 23:58
 **/
public class MyServletRequestAsyncListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAsyncListener.onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAsyncListener.onTimeout");

        ServletResponse response = event.getSuppliedResponse();
        PrintWriter writer = response.getWriter();
        writer.println("timeout, please try again later...");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAsyncListener.onError");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("com.kilogate.hi.servlet.listener.MyServletRequestAsyncListener.onStartAsync");
    }
}
