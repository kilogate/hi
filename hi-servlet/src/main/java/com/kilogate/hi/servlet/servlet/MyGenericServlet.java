package com.kilogate.hi.servlet.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyGenericServlet
 *
 * @author kilogate
 * @create 2021/12/4 17:33
 **/
@WebServlet(name = "myGenericServlet",
        urlPatterns = {"/myGenericServlet"},
        initParams = {
                @WebInitParam(name = "admin", value = "Xxx"),
                @WebInitParam(name = "email", value = "xxx@xxx.com")
        })
public class MyGenericServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();

        PrintWriter writer = res.getWriter();
        writer.println("admin: " + servletConfig.getInitParameter("admin"));
        writer.println("email: " + servletConfig.getInitParameter("email"));
    }
}
