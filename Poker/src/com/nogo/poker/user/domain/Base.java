package com.nogo.poker.user.domain;

import static org.apache.commons.lang3.StringUtils.join;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public abstract class Base {

  /**
   * Constructs the base domain object.
   *
   * @param builder The builder containing fields to set
   */
  public Base(final AbstractBuilder<?> builder) {
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

  @Override
  public String toString() {
    final List<String> print = new ArrayList<>();
    print.add("id: " + id);
    print.add("createdDate: " + createdDate);
    print.add("modifiedDate: " + modifiedDate);
    return join(print, ", ");
  }

  abstract static class AbstractBuilder<T> {
    private String id;
    private DateTime createdDate;
    private DateTime modifiedDate;

    abstract T self();

    public T withValues(final Base base) {
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
