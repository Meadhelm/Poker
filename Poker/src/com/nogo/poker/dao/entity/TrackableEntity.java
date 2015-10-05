package com.nogo.poker.dao.entity;

import static org.joda.time.DateTimeZone.UTC;

import com.nogo.poker.domain.Trackable;

import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "trackable")
@PrimaryKeyJoinColumn(name = "id")
public abstract class TrackableEntity extends ResourceEntity {

  public TrackableEntity() {
    super();
  }

  /**
   * Constructs a new TrackableEntity from a trackable object.
   *
   * @param trackable The trackable object to source field data from.
   */
  public TrackableEntity(final Trackable trackable) {
    super();
    if (trackable != null) {
      this.effectiveDate = trackable.getEffectiveDate().toDate();
      this.endDate = trackable.getEndDate().toDate();
    }
  }

  @Column(name = "effective_date", updatable = false)
  private Date effectiveDate;

  @Column(name = "end_date")
  private Date endDate;

  public DateTime getEffectiveDate() {
    return new DateTime(effectiveDate, UTC);
  }

  public void setEffectiveDate(final DateTime effectiveDate) {
    this.effectiveDate = effectiveDate.toDate();
  }

  public DateTime getEndDate() {
    return new DateTime(endDate, UTC);
  }

  public void setEndDate(final DateTime endDate) {
    this.endDate = endDate.toDate();
  }
}
