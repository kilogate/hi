package com.kilogate.hi.servlet.decorator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DecoratorTestServlet
 *
 * @author kilogate
 * @create 2021/12/5 01:13
 **/
@WebServlet("/decoratorTestServlet")
public class DecoratorTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        resp.getWriter().println(name);
    }
}
