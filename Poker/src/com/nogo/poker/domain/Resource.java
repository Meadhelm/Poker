package com.nogo.poker.domain;

import com.nogo.poker.dao.entity.ResourceEntity;
import com.nogo.poker.web.dto.ResourceDto;

import org.joda.time.DateTime;

public abstract class Resource {

  /**
   * Constructs the base domain object.
   *
   * @param builder The builder containing fields to set
   */
  public Resource(final AbstractBuilder<?> builder) {
    id = builder.id;
    createdDate = builder.createdDate;
    modifiedDate = builder.modifiedDate;
  }

  private final String id;
  private final DateTime createdDate;
  private final DateTime modifiedDate;

  public String getId() {
    return id;
  }

  public DateTime getCreatedDate() {
    return createdDate;
  }

  public DateTime getModifiedDate() {
    return modifiedDate;
  }

  public abstract ResourceEntity toEntity();

  public abstract ResourceDto toDto();

  public abstract static class AbstractBuilder<T> {
    private String id;
    private DateTime createdDate;
    private DateTime modifiedDate;

    public abstract T self();

    /**
     * Copies all values from the base domain object into the base builder.
     *
     * @param base domain object
     * @return builder object
     */
    public T withValues(final Resource base) {
      this.id = base.getId();
      this.createdDate = base.getCreatedDate();
      this.modifiedDate = base.getModifiedDate();
      return self();
    }

    public T withId(final String id) {
      this.id = id;
      return self();
    }

    public T withCreatedDate(final DateTime createdDate) {
      this.createdDate = createdDate;
      return self();
    }

    public T withModifiedDate(final DateTime modifiedDate) {
      this.modifiedDate = modifiedDate;
      return self();
    }
  }
}
