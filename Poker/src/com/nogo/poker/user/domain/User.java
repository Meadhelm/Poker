package com.nogo.poker.user.domain;

import static org.apache.commons.lang3.StringUtils.join;

import org.hibernate.validator.constraints.Email;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

public class User extends Base {

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

  @Override
  public String toString() {
    final List<String> print = new ArrayList<>();
    print.add(super.toString());
    print.add("firstName: " + firstName);
    print.add("lastName: " + lastName);
    return join(print, ", ");
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

  abstract static class AbstractBuilder<T> extends Base.AbstractBuilder<T> {
    private String firstName;
    private String lastName;
    private String email;

    public T withValues(final User user) {
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
