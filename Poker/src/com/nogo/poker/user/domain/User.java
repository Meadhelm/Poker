package com.nogo.poker.user.domain;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

public class User {
  @Size(min = 0, max = 64)
  private String name;

  @Email
  private String email;

  private DateTime createdDate;

  private DateTime modifiedDate;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
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
    print.add("name: " + name);
    print.add("createdDate: " + createdDate);
    print.add("modifiedDate: " + modifiedDate);
    return StringUtils.join(print, ", ");
  }

  public User() {

  }

  public User(final AbstractBuilder<Builder> builder) {
    name = builder.name;
    email = builder.email;
    createdDate = builder.createdDate;
    modifiedDate = builder.modifiedDate;
  }

  public static class Builder extends AbstractBuilder<Builder> {
    @Override
    public Builder self() {
      return this;
    }

    public User build() {
      return new User(this);
    }
  }

  abstract static class AbstractBuilder<T> {
    private String name;
    private String email;
    private DateTime createdDate;
    private DateTime modifiedDate;

    abstract T self();

    public T withValues(final User userDto) {
      this.name = userDto.getName();
      return self();
    }

    public T withName(final String name) {
      this.name = name;
      return self();
    }

    public T withEmail(final String email) {
      this.email = email;
      return self();
    }

    public T withCreatedDate(final DateTime createdDate) {
      this.createdDate = createdDate;
      return self();
    }

    public T withCreatedDate(final String createdDate) {
      if (StringUtils.isNotBlank(createdDate)) {
        this.createdDate = DateTime.parse(createdDate);
      }
      return self();
    }

    public T withModifiedDate(final DateTime modifiedDate) {
      this.modifiedDate = modifiedDate;
      return self();
    }

    public T withModifiedDate(final String modifiedDate) {
      if (StringUtils.isNotBlank(modifiedDate)) {
        this.createdDate = DateTime.parse(modifiedDate);
      }
      return self();
    }
  }
}
