package com.nogo.poker.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public final class PokerInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(final ServletContext container) throws ServletException {
    // Create the dispatcher servlet's Spring application context
    final AnnotationConfigWebApplicationContext dispatcherContext =
        new AnnotationConfigWebApplicationContext();
    dispatcherContext.register(DispatcherConfig.class);

    // Register and map the dispatcher servlet
    final ServletRegistration.Dynamic dispatcher =
        container.addServlet("Poker", new DispatcherServlet(dispatcherContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
  }
}
