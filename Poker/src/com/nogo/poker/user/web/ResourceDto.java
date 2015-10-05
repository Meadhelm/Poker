package com.nogo.poker.user.web;

import com.nogo.poker.user.domain.Base;

public class ResourceDto {

  private String id;
  private String createdDate;
  private String modifiedDate;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(final String createdDate) {
    this.createdDate = createdDate;
  }

  public String getModifiedDate() {
    return modifiedDate;
  }

  public void setModifiedDate(final String modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public ResourceDto() {

  }

  /**
   * Constructs the base resource dto object.
   *
   * @param base The base object containing fields to set
   */
  public ResourceDto(final Base base) {
    if (base != null) {
      this.id = base.getId();
      this.createdDate = base.getCreatedDate() == null ? null : base.getCreatedDate().toString();
      this.modifiedDate = base.getModifiedDate() == null ? null : base.getModifiedDate().toString();
    }
  }
}
