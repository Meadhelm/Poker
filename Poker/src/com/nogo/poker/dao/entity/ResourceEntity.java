package com.nogo.poker.dao.entity;

import static org.joda.time.DateTimeZone.UTC;

import com.nogo.poker.domain.Resource;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ResourceEntity {

  public ResourceEntity() {

  }

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "is_deleted")
  private boolean deleted;

  @Column(name = "created_timestamp", updatable = false)
  @Generated(GenerationTime.INSERT)
  private Date createdTimestamp;

  @Column(name = "modified_timestamp")
  @Generated(GenerationTime.ALWAYS)
  private Date modifiedTimestamp;

  public String getId() {
    return id;
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

  public DateTime getModifiedTimestamp() {
    return new DateTime(modifiedTimestamp, UTC);
  }

  public abstract Resource toDomain();
}
