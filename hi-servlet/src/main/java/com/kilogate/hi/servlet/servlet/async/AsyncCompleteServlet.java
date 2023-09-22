package com.kilogate.hi.servlet.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异步完成的Servlet
 *
 * @author kilogate
 * @create 2021/12/5 15:24
 **/
@WebServlet(urlPatterns = "/asyncCompleteServlet", asyncSupported = true)
public class AsyncCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Async Servlet</title></head>");
        writer.println("<body><div id='progress'></div>");

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(60000);

        // 业务逻辑放到异步线程去执行，释放Tomcat请求线程资源（在客户端来看，其实和同步没有差别）
        asyncContext.start(() -> {
            System.out.println("new thread: " + Thread.currentThread());
            for (int i = 0; i < 10; i++) {
                writer.println("<script>");
                writer.println("document.getElementById('progress').innerHTML = '" + (i * 10) + "% complete'");
                writer.println("</script>");
                writer.flush();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            writer.println("<script>");
            writer.println("document.getElementById('progress').innerHTML = 'Done'");
            writer.println("</script>");
            writer.println("</body></html>");

            asyncContext.complete();
        });
    }
}
