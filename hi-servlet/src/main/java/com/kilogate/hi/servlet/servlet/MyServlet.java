package com.kilogate.hi.servlet.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyServlet
 *
 * @author kilogate
 * @create 2021/12/4 15:05
 **/
@WebServlet(name = "MyServlet", urlPatterns = {"/myServlet"})
public class MyServlet implements Servlet {
    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) {
        System.out.println("com.kilogate.hi.servlet.servlet.MyServlet.init");
        this.servletConfig = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("com.kilogate.hi.servlet.servlet.MyServlet.getServletConfig");
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        System.out.println("com.kilogate.hi.servlet.servlet.MyServlet.service");

        PrintWriter writer = res.getWriter();
        writer.println("hello servlet");
    }

    @Override
    public String getServletInfo() {
        System.out.println("com.kilogate.hi.servlet.servlet.MyServlet.getServletInfo");
        return "MyServlet";
    }

    @Override
    public void destroy() {
        System.out.println("com.kilogate.hi.servlet.servlet.MyServlet.destroy");
    }
}
