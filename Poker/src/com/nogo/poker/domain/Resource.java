package com.nogo.poker.domain;

import static org.joda.time.DateTimeZone.UTC;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.nogo.poker.time.DateTimeSerializer;

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
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.NONE)
public abstract class Resource {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;

  @Column(name = "is_deleted", nullable = false)
  private Boolean deleted = false;

  @Column(name = "created_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      insertable = false, updatable = false)
  private Date createdTimestamp;

  @Column(name = "modified_timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
      insertable = false)
  private Date modifiedTimestamp;

  @JsonGetter
  public String getId() {
    return id;
  }

  protected void setId(final String id) {
    this.id = id;
  }

  @JsonGetter
  public Boolean isDeleted() {
    return deleted;
  }

  protected void setDeleted(final Boolean deleted) {
    this.deleted = deleted;
  }

  @JsonGetter
  @JsonSerialize(using = DateTimeSerializer.class)
  public DateTime getCreatedTimestamp() {
    return new DateTime(createdTimestamp, UTC);
  }

  protected void setCreatedTimestamp(final Date createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  @JsonGetter
  @JsonSerialize(using = DateTimeSerializer.class)
  public DateTime getModifiedTimestamp() {
    return new DateTime(modifiedTimestamp, UTC);
  }

  protected void setModifiedTimestamp(final Date modifiedTimestamp) {
    this.modifiedTimestamp = modifiedTimestamp;
  }

}
