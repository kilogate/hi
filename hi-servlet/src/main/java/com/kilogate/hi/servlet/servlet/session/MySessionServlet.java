package com.kilogate.hi.servlet.servlet.session;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MySessionServlet
 *
 * @author kilogate
 * @create 2021/12/4 20:06
 **/
@WebServlet(urlPatterns = "/mySessionServlet")
public class MySessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        System.out.printf("session id: %s", session.getId());


        Object a = session.getAttribute("a");
        System.out.println(a);

        session.setAttribute("a", "A");

        PrintWriter writer = resp.getWriter();
        writer.println("session.getId()");
    }
}
