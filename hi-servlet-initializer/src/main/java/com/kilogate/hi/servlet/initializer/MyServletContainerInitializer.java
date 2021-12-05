package com.kilogate.hi.servlet.initializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * MyServletContainerInitializer
 *
 * @author kilogate
 * @create 2021/12/6 00:19
 **/
@HandlesTypes({UsefulServlet.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("com.kilogate.hi.servlet.initializer.MyServletContainerInitializer.onStartup");

        ServletRegistration.Dynamic servletRegistration = ctx.addServlet("usefulServlet", UsefulServlet.class);
        servletRegistration.addMapping("/usefulServlet");
    }
}
