package com.kilogate.hi.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * MyFilter
 *
 * @author kilogate
 * @create 2021/12/4 23:59
 **/
//@WebFilter(filterName = "myFilter", urlPatterns = {"/*"}) open this for test
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("com.kilogate.hi.servlet.filter.MyFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        System.out.println("com.kilogate.hi.servlet.filter.MyFilter.doFilter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("com.kilogate.hi.servlet.filter.MyFilter.destroy");
    }
}
