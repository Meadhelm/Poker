package com.nogo.poker.web.dto;

import com.nogo.poker.domain.IDomainObjectAware;
import com.nogo.poker.user.domain.User;

import org.hibernate.validator.constraints.NotEmpty;

public class UserDto extends ResourceDto implements IDomainObjectAware<User> {

  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  private String email;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  @Override
  public User toDomainObject() {
    return new User.Builder().withFirstName(firstName).withLastName(lastName).withEmail(email)
        .build();
  }

  public UserDto() {

  }

  /**
   * Constructs the user dto object.
   *
   * @param user The user object containing fields to set
   */
  public UserDto(final User user) {
    super(user);
    if (user != null) {
      this.firstName = user.getFirstName();
      this.lastName = user.getLastName();
      this.email = user.getEmail();
    }
  }
}
