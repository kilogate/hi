package com.kilogate.hi.servlet.servlet.async;

import com.kilogate.hi.servlet.listener.MyServletRequestAsyncListener;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步转发的Servlet
 * 注意：所有的 Filter 也得支持 asyncSupported
 *
 * @author kilogate
 * @create 2021/12/5 12:10
 **/
@WebServlet(urlPatterns = "/asyncDispatchServlet", asyncSupported = true)
public class AsyncDispatchServlet extends HttpServlet {
    private ExecutorService executorService = Executors.newFixedThreadPool(500);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mainThread", Thread.currentThread().getName());

        AsyncContext asyncContext = req.startAsync();
        asyncContext.setTimeout(5000); // 如果超时，就是Tomcat的兜底报错页面了
        asyncContext.addListener(new MyServletRequestAsyncListener());


        // 默认使用的还是Servlet线程池资源，建议使用自定义的线程池不要占用Servlet线程池资源

        // case1：使用默认线程池
//        asyncContext.start(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            req.setAttribute("workThread", Thread.currentThread().getName());
//
//            asyncContext.dispatch("/threadName.jsp");
//        });

        // case2: 使用自定义线程池
        executorService.execute(() -> {
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
