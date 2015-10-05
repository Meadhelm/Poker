package com.nogo.poker.dao.entity;

import static org.joda.time.DateTimeZone.UTC;

import com.nogo.poker.domain.Trackable;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "trackable")
public class TrackableEntity {

  public TrackableEntity() {

  }

  /**
   * Constructs a new TrackableEntity from a trackable object.
   *
   * @param trackable The trackable object to source field data from.
   */
  public TrackableEntity(final Trackable trackable, final ResourceEntity resource) {
    if (trackable != null) {
      if (trackable.getEffectiveDate() != null) {
        this.effectiveDate = trackable.getEffectiveDate().toDate();
      }
      if (trackable.getEndDate() != null) {
        this.endDate = trackable.getEndDate().toDate();
      }
    }
    this.id = trackable.getId();
    this.resource = resource;
  }

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "gen")
  @GenericGenerator(name = "gen", strategy = "foreign",
      parameters = @Parameter(name = "property", value = "resource") )
  private String id;

  @Column(name = "effective_date", updatable = false)
  private Date effectiveDate;

  @Column(name = "end_date")
  private Date endDate;

  @OneToOne
  @PrimaryKeyJoinColumn
  private ResourceEntity resource;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

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

  public ResourceEntity getResource() {
    return resource;
  }

  public void setResource(final ResourceEntity resource) {
    this.resource = resource;
  }

  public ResourceEntity getResourceEntity() {
    return this.resource;
  }
}
