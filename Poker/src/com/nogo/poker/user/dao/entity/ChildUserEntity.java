package com.nogo.poker.user.dao.entity;

import com.nogo.poker.user.domain.ChildUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "child_user")
@PrimaryKeyJoinColumn(name = "id")
public class ChildUserEntity extends UserEntity {

  public ChildUserEntity() {
    super();
  }

  /**
   * Constructs a new UserEntity from a user object.
   *
   * @param user The user object to source field data from.
   */
  public ChildUserEntity(final ChildUser user) {
    super(user);
    if (user != null) {
      this.isFlying = user.isFlying();

    }
  }

  @Column(name = "is_flying", nullable = false)
  private boolean isFlying;

  public boolean isFlying() {
    return isFlying;
  }

  public void setFlying(final boolean isFlying) {
    this.isFlying = isFlying;
  }

  @Override
  public ChildUser toDomain() {
    return new ChildUser.Builder().withValues(super.toDomain()).isFlying(isFlying).build();
  }

}
