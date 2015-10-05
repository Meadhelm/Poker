package com.nogo.poker.dao.entity;

import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity {

  public BaseEntity() {
    final Date now = new Date();
    createdTimestamp = now;
    modifiedTimestamp = now;
  }

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  @Column(name = "id", nullable = false)
  private String id;

  @Column(name = "created_timestamp", nullable = false, updatable = false)
  private final Date createdTimestamp;

  @Column(name = "modified_timestamp", nullable = false)
  private final Date modifiedTimestamp;

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public DateTime getCreatedTimestamp() {
    if (createdTimestamp != null) {
      return new DateTime(createdTimestamp);
    }
    return null;
  }

  public DateTime getModifiedTimestamp() {
    if (modifiedTimestamp != null) {
      return new DateTime(modifiedTimestamp);
    }
    return null;
  }
}
