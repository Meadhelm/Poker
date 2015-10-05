package com.nogo.poker.configuration;

import org.springframework.core.env.Environment;

public class PropertyResolver {
  private final Environment env;

  protected PropertyResolver(final Environment env) {
    this.env = env;
  }

  public String getPropery(final String key) {
    return env.getProperty(key);
  }
}
