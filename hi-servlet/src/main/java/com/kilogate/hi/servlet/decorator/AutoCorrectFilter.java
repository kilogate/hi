package com.kilogate.hi.servlet.decorator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * AutoCorrectFilter
 *
 * @author kilogate
 * @create 2021/12/5 01:09
 **/
//@WebFilter(urlPatterns = "/*") open this for test
public class AutoCorrectFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 将HttpServletRequest包装为AutoCorrectHttpServletRequestWrapper
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        AutoCorrectHttpServletRequestWrapper httpServletRequestWrapper = new AutoCorrectHttpServletRequestWrapper(httpServletRequest);
        chain.doFilter(httpServletRequestWrapper, response);
    }

    @Override
    public void destroy() {

    }
}
