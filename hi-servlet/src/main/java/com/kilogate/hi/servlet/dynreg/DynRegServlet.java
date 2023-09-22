package com.kilogate.hi.servlet.dynreg;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 测试用的Servlet
 *
 * @author kilogate
 * @create 2021/12/6 00:11
 **/
public class DynRegServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("DynRegServlet");
    }
}
