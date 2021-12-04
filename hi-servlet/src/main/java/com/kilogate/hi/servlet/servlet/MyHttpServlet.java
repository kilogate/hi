package com.kilogate.hi.servlet.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyHttpServlet
 *
 * @author kilogate
 * @create 2021/12/4 17:38
 **/
@WebServlet(name = "myHttpServlet", urlPatterns = "/myHttpServlet")
public class MyHttpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("com.kilogate.hi.servlet.servlet.MyHttpServlet.doGet");
    }
}
