package com.nogo.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "id")
@JsonDeserialize(builder = User.AbstractBuilder.class)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = FlyingUser.class, name = "flyingUser")})
public abstract class User extends Resource {

  protected User() {

  }

  public <B, T> User(final AbstractBuilder<B, T> builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.emailAddress = builder.emailAddress;
  }

  @Column(name = "first_name", nullable = false, length = 50)
  @JsonProperty
  @Size(min = 2, max = 50)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 50)
  @JsonProperty
  @Size(min = 1, max = 50)
  private String lastName;

  @Column(name = "email_address", nullable = false, length = 50)
  @JsonProperty
  @Email
  @Size(min = 5, max = 50)
  private String emailAddress;

  public String getFirstName() {
    return firstName;
  }

  protected void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  protected void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  protected void setEmailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
  public abstract static class AbstractBuilder<B, T> {

    private String firstName;
    private String lastName;
    private String emailAddress;

    abstract T build();

    public B withFirstName(final String firstName) {
      this.firstName = firstName;
      return self();
    }

    public B withLastName(final String lastName) {
      this.lastName = lastName;
      return self();
    }

    public B withEmailAddress(final String emailAddress) {
      this.emailAddress = emailAddress;
      return self();
    }

    abstract B self();
  }

}
