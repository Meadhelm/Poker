package com.nogo.poker.web.dto;

import com.nogo.poker.user.domain.ChildUser;

public class ChildUserDto extends UserDto {

  public static final String TYPE = "child";

  private boolean flying;

  public boolean isFlying() {
    return flying;
  }

  public void setFlying(final boolean flying) {
    this.flying = flying;
  }

  @Override
  public String getType() {
    return TYPE;
  }

  @Override
  public ChildUser toDomain() {
    return new ChildUser.Builder().withValues(super.toDomain()).isFlying(flying).build();
  }

  public ChildUserDto() {

  }

  /**
   * Constructs the user dto object.
   *
   * @param user The user object containing fields to set
   */
  public ChildUserDto(final ChildUser user) {
    super(user);
    if (user != null) {
      this.flying = user.isFlying();
    }
  }
}
