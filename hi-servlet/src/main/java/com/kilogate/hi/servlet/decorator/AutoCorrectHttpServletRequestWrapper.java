package com.kilogate.hi.servlet.decorator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * AutoCorrectHttpServletRequestWrapper
 *
 * @author kilogate
 * @create 2021/12/5 01:11
 **/
public class AutoCorrectHttpServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public AutoCorrectHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);

        if (value != null && !value.isEmpty()) {
            value = value.trim();
        }

        return value;
    }
}
