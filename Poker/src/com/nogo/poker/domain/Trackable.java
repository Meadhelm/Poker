package com.nogo.poker.domain;

import org.joda.time.DateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

public abstract class Trackable extends Resource {

  /**
   * Constructs the trackable domain object.
   *
   * @param builder The builder containing fields to set
   */
  public Trackable(final AbstractBuilder<?> builder) {
    super(builder);
    this.effectiveDate = builder.effectiveDate;
    this.endDate = builder.endDate;
  }

  @NotNull
  private final DateTime effectiveDate;

  @NotNull
  @Future
  private final DateTime endDate;

  public DateTime getEffectiveDate() {
    return effectiveDate;
  }

  public DateTime getEndDate() {
    return endDate;
  }

  public abstract static class AbstractBuilder<T> extends Resource.AbstractBuilder<T> {
    private DateTime effectiveDate;
    private DateTime endDate;

    /**
     * Copies all values from the trackable domain object into the trackable builder.
     *
     * @param trackable domain object
     * @return builder object
     */
    public T withValues(final Trackable trackable) {
      super.withValues(trackable);
      this.effectiveDate = trackable.getEffectiveDate();
      this.endDate = trackable.getEndDate();
      return self();
    }

    public T withEffectiveDate(final DateTime effectiveDate) {
      this.effectiveDate = effectiveDate;
      return self();
    }

    public T withEndDate(final DateTime endDate) {
      this.endDate = endDate;
      return self();
    }
  }
}
