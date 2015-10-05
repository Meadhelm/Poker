package com.nogo.poker.dao.entity;

import static org.joda.time.DateTimeZone.UTC;

import com.nogo.poker.domain.Resource;
import com.nogo.poker.domain.Trackable;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ResourceEntity {

  public ResourceEntity() {

  }

  /**
   * Initializes base properties.
   *
   * @param resource the resource to copy
   */
  public ResourceEntity(final Resource resource, final Trackable trackable) {
    this.id = resource.getId();
    this.deleted = false;
    if (resource.getCreatedDate() != null) {
      this.createdTimestamp = resource.getCreatedDate().toDate();
    }
    if (resource.getModifiedDate() != null) {
      this.modifiedTimestamp = resource.getModifiedDate().toDate();
    }
    if (trackable != null) {
      this.trackable = new TrackableEntity(trackable, this);
    }
  }

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "is_deleted", nullable = false)
  private boolean deleted;

  @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      insertable = false, updatable = false)
  private Date createdTimestamp;

  @Column(name = "modified_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      insertable = false)
  private Date modifiedTimestamp;

  @OneToOne(mappedBy = "resource", cascade = CascadeType.ALL)
  private TrackableEntity trackable;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(final boolean deleted) {
    this.deleted = deleted;
  }

  public DateTime getCreatedTimestamp() {
    return new DateTime(createdTimestamp, UTC);
  }

  public void setCreatedTimestamp(final Date createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public DateTime getModifiedTimestamp() {
    return new DateTime(modifiedTimestamp, UTC);
  }

  public void setModifiedTimestamp(final Date modifiedTimestamp) {
    this.modifiedTimestamp = modifiedTimestamp;
  }

  public TrackableEntity getTrackable() {
    return trackable;
  }

  public void setTrackable(final TrackableEntity trackable) {
    this.trackable = trackable;
  }

  public abstract Resource toDomain();
}
