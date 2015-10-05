package com.nogo.poker.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.nogo.poker.configuration.JsonJodaDateTimeSerializer;
import com.nogo.poker.domain.Resource;

import org.joda.time.DateTime;

public class ResourceDto {

  @JsonProperty
  private String id;

  @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
  @JsonProperty
  private DateTime createdDate;

  @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
  @JsonProperty
  private DateTime modifiedDate;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public DateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final DateTime createdDate) {
    this.createdDate = createdDate;
  }

  public DateTime getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(final DateTime modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public ResourceDto() {

  }

  /**
   * Constructs the base resource dto object.
   *
   * @param resource The base object containing fields to set
   */
  public ResourceDto(final Resource resource) {
    if (resource != null) {
      this.id = resource.getId();
      this.createdDate = resource.getCreatedDate();
      this.modifiedDate = resource.getModifiedDate();
    }
  }
}
