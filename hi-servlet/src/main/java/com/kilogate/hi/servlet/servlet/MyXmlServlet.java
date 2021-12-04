package com.kilogate.hi.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyXmlServlet
 *
 * @author kilogate
 * @create 2021/12/4 17:41
 **/
public class MyXmlServlet extends HttpServlet {
    @Override
    public void init() {
        System.out.println("com.kilogate.hi.servlet.servlet.MyXmlServlet.init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("com.kilogate.hi.servlet.servlet.MyXmlServlet.doGet");
    }
}