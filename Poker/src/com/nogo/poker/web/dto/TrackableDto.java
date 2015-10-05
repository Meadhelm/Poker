package com.nogo.poker.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.nogo.poker.configuration.JsonJodaDateTimeSerializer;
import com.nogo.poker.domain.Trackable;

import org.joda.time.DateTime;

public class TrackableDto extends ResourceDto {

  @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
  @JsonProperty
  private DateTime effectiveDate;

  @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
  @JsonProperty
  private DateTime endDate;

  public DateTime getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(final DateTime effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public DateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(final DateTime endDate) {
    this.endDate = endDate;
  }

  public TrackableDto() {

  }

  /**
   * Constracts a trackable dto from a domain object.
   *
   * @param trackable domain object
   */
  public TrackableDto(final Trackable trackable) {
    if (trackable != null) {
      this.effectiveDate = trackable.getEffectiveDate();
      this.endDate = trackable.getEndDate();
    }
  }
}
