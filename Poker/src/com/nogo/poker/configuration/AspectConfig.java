package com.nogo.poker.configuration;

import com.nogo.poker.logging.EndpointLogging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {

  @Bean
  public EndpointLogging logDebugInterceptor() {
    return new EndpointLogging();
  }

}
