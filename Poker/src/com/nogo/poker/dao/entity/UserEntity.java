package com.nogo.poker.dao.entity;

import com.nogo.poker.common.IDomainObjectAware;
import com.nogo.poker.user.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity implements IDomainObjectAware<User> {

  public UserEntity() {
    super();
  }

  /**
   * Constructs a new UserEntity from a user object.
   *
   * @param user The user object to source field data from.
   */
  public UserEntity(final User user) {
    super();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.emailAddress = user.getEmail();
  }

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email_address", nullable = false)
  private String emailAddress;

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

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(final String emailAddress) {
    this.emailAddress = emailAddress;
  }

  @Override
  public User toDomainObject() {
    return new User.Builder().withCreatedDate(getCreatedTimestamp())
        .withModifiedDate(getModifiedTimestamp()).withEmail(emailAddress).withFirstName(firstName)
        .withLastName(lastName).withId(getId()).build();
  }

}
