package com.kilogate.hi.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * MyCookieServlet
 *
 * @author kilogate
 * @create 2021/12/4 17:48
 **/
@WebServlet(urlPatterns = "/myCookieServlet")
public class MyCookieServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 遍历cookie
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            String name = cookie.getName();
            String value = cookie.getValue();
            String domain = cookie.getDomain();
            String path = cookie.getPath();
            int maxAge = cookie.getMaxAge();
            System.out.printf("name: %s, value: %s, domain: %s, path: %s, maxAge: %s%n", name, value, domain, path, maxAge);
        }

//        // 设置cookie
//        Cookie cookie = new Cookie("a", "b");
//        resp.addCookie(cookie);

//        // 删除cookie
//        Cookie cookie = new Cookie("a", "b");
//        cookie.setMaxAge(0);
//        resp.addCookie(cookie);

        PrintWriter writer = resp.getWriter();
        writer.println("com.kilogate.hi.servlet.cookie.MyCookieServlet.doGet");
    }
}
