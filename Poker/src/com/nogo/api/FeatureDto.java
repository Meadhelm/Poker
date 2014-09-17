package com.nogo.api;

import java.util.List;

public class FeatureDto {

  private String name;
  private boolean released;
  private List<EndpointDto> endpoints;

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public boolean isReleased() {
    return released;
  }

  public void setReleased(final boolean released) {
    this.released = released;
  }

  public List<EndpointDto> getEndpoints() {
    return endpoints;
  }

  public void setEndpoints(final List<EndpointDto> endpoints) {
    this.endpoints = endpoints;
  }
}
