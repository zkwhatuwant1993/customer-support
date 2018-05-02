package com.zk.filter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Configurator implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context =  servletContextEvent.getServletContext();
        FilterRegistration.Dynamic registration = context.addFilter("authenticationFilter",
            new AuthenticationFilter());
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(null,false,"/sessions","/tickets");
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
