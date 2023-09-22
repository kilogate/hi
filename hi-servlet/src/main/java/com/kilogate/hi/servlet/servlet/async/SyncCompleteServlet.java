package com.kilogate.hi.servlet.servlet.async;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 同步完成的Servlet
 *
 * @author kilogate
 * @create 2021/12/5 01:13
 **/
@WebServlet("/syncCompleteServlet")
public class SyncCompleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<html><head><title>Async Servlet</title></head>");
        writer.println("<body><div id='progress'></div>");

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
    }
}
