package com.kilogate.hi.servlet.initializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UsefulServlet
 *
 * @author kilogate
 * @create 2021/12/6 00:20
 **/
public class UsefulServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("UsefulServlet");
    }
}
