package com.kilogate.hi.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * MyTestServlet
 *
 * @author kilogate
 * @create 2021/12/4 20:19
 **/
@WebServlet(urlPatterns = "/myTestServlet")
public class MyTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (Objects.equals(action, "attributeAdded")) {
            ServletContext servletContext = this.getServletContext();
            servletContext.setAttribute("a", "A");
        } else if (Objects.equals(action, "attributeRemoved")) {
            ServletContext servletContext = this.getServletContext();
            servletContext.removeAttribute("a");
        } else if (Objects.equals(action, "attributeReplaced")) {
            ServletContext servletContext = this.getServletContext();
            servletContext.setAttribute("a", "A");
            servletContext.setAttribute("a", "A");
        } else if (Objects.equals(action, "sessionCreated")) {
            HttpSession session = req.getSession();
            System.out.println(session.getId());
        } else if (Objects.equals(action, "sessionDestroyed")) {
            HttpSession session = req.getSession();
            session.invalidate();
        } else if (Objects.equals(action, "valueBound")) {
            HttpSession session = req.getSession();
            session.setAttribute("a", new MyHttpSessionBindingListener());
        } else if (Objects.equals(action, "valueUnbound")) {
            HttpSession session = req.getSession();
            session.setAttribute("a", null);
        }

        PrintWriter writer = resp.getWriter();
        writer.println("done");
    }
}
