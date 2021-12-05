package com.kilogate.hi.servlet.dynreg;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

/**
 * DynRegListener
 *
 * @author kilogate
 * @create 2021/12/6 00:12
 **/
@WebListener
public class DynRegListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        DynRegServlet servlet = null;
        try {
            servlet = servletContext.createServlet(DynRegServlet.class);
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if (servlet != null && servlet instanceof DynRegServlet) {
            ServletRegistration.Dynamic servletRegistration = servletContext.addServlet("dynRegServlet", servlet);
            servletRegistration.addMapping("/dynRegServlet");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
