package com.kilogate.hi.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AsyncDispatchServlet
 *
 * @author kilogate
 * @create 2021/12/5 12:10
 **/
// 注意：所有的 Filter 也得支持 asyncSupported
@WebServlet(urlPatterns = "/asyncDispatchServlet", asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mainThread", Thread.currentThread().getName());

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(5000);
        asyncContext.start(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            req.setAttribute("workThread", Thread.currentThread().getName());

            asyncContext.dispatch("/threadName.jsp");
        });
    }
}
