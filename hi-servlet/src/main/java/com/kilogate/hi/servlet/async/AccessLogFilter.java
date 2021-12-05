package com.kilogate.hi.servlet.async;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * AccessLogFilter
 *
 * @author kilogate
 * @create 2021/12/5 01:33
 **/
@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class AccessLogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Thread currentThread = Thread.currentThread();

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        System.out.printf("%s [%s] %s%n", new Date(), currentThread.getName(), requestURI);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
