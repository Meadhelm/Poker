package com.nogo.poker.web.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import com.nogo.poker.user.domain.User;

import org.hibernate.validator.constraints.NotEmpty;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.PROPERTY, property = "type")
@JsonSubTypes({@Type(value = ChildUserDto.class, name = ChildUserDto.TYPE),
    @Type(value = UserDto.class, name = UserDto.TYPE)})
public class UserDto extends TrackableDto {

  public static final String TYPE = "user";

  @NotEmpty
  private String firstName;
  @NotEmpty
  private String lastName;
  @NotEmpty
  private String email;

  @SuppressWarnings("unused")
  private String type;

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

  public String getType() {
    return TYPE;
  }

  /**
   * Sets the type to expected TYPE, otherwise UNKOWN.
   *
   * @param type the input type
   */
  public void setType(final String type) {
    if (TYPE.equals(type)) {
      this.type = TYPE;
    }
  }

  public User toDomain() {
    return new User.Builder().withFirstName(firstName).withLastName(lastName).withEmail(email)
        .withEffectiveDate(getEffectiveDate()).withEndDate(getEndDate()).withId(getId()).build();
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
      this.setEffectiveDate(user.getEffectiveDate());
      this.setEndDate(user.getEndDate());
      this.setId(user.getId());
      this.setCreatedDate(user.getCreatedDate());
      this.setModifiedDate(user.getModifiedDate());
    }
  }
}
