package com.nogo.poker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:/messages.properties")
public class PropertyConfig {
  @Autowired
  private Environment env;

  @Bean
  public PropertyResolver propertyResolver() {
    return new PropertyResolver(env);
  }
}
