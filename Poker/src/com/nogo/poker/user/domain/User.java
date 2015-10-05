package com.nogo.poker.user.domain;

import com.nogo.poker.domain.Trackable;
import com.nogo.poker.user.dao.entity.UserEntity;
import com.nogo.poker.web.dto.UserDto;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

public class User extends Trackable {

  public static final String TYPE = "user";

  /**
   * Constructs the user domain object.
   *
   * @param builder The builder containing fields to set
   */
  public User(final AbstractBuilder<?> builder) {
    super(builder);
    firstName = builder.firstName;
    lastName = builder.lastName;
    email = builder.email;
  }

  @Size(min = 0, max = 64)
  private final String firstName;

  @Size(min = 1, max = 64)
  private final String lastName;

  @Email
  private final String email;

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getType() {
    return TYPE;
  }

  @Override
  public UserDto toDto() {
    return new UserDto(this);
  }

  @Override
  public UserEntity toEntity() {
    return new UserEntity(this);
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

  public abstract static class AbstractBuilder<T> extends Trackable.AbstractBuilder<T> {
    private String firstName;
    private String lastName;
    private String email;

    /**
     * Copies all values from the user domain object into the user builder.
     *
     * @param user domain object
     * @return builder object
     */
    public T withValues(final User user) {
      super.withValues(user);
      this.firstName = user.getFirstName();
      this.lastName = user.getLastName();
      this.email = user.getEmail();
      return self();
    }

    public T withFirstName(final String firstName) {
      this.firstName = firstName;
      return self();
    }

    public T withLastName(final String lastName) {
      this.lastName = lastName;
      return self();
    }

    public T withEmail(final String email) {
      this.email = email;
      return self();
    }
  }
}
