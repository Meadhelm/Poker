package com.nogo.poker.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "child_user")
@PrimaryKeyJoinColumn(name = "id")
@JsonDeserialize(builder = FlyingUser.Builder.class)
@JsonTypeName("POKER")
public class FlyingUser extends User {

  protected FlyingUser() {

  }

  public FlyingUser(final Builder builder) {
    super(builder);
    this.flying = builder.flying;
  }

  @Column(name = "is_flying", nullable = false)
  @JsonProperty
  private boolean flying;

  public boolean isFlying() {
    return flying;
  }

  protected void setFlying(final boolean flying) {
    this.flying = flying;
  }

  @JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
  public static class Builder extends User.AbstractBuilder<Builder, FlyingUser> {

    private boolean flying;

    public Builder withFlying(final boolean flying) {
      this.flying = flying;
      return this;
    }

    @Override
    public FlyingUser build() {
      return new FlyingUser(this);
    }

    @Override
    public Builder self() {
      return this;
    }

  }


}
