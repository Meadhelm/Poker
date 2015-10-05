package com.nogo.poker.user.domain;

import static org.apache.commons.lang3.StringUtils.join;

import com.nogo.poker.user.dao.entity.ChildUserEntity;
import com.nogo.poker.web.dto.ChildUserDto;

import java.util.ArrayList;
import java.util.List;

public class ChildUser extends User {

  public ChildUser(final AbstractBuilder<?> builder) {
    super(builder);
    this.flying = builder.flying;
  }

  private final boolean flying;

  public boolean isFlying() {
    return flying;
  }

  @Override
  public String toString() {
    final List<String> print = new ArrayList<>();
    print.add(super.toString());
    print.add("flying: " + flying);
    return join(print, ", ");
  }

  @Override
  public ChildUserDto toDto() {
    return new ChildUserDto(this);
  }

  @Override
  public ChildUserEntity toEntity() {
    return new ChildUserEntity(this);
  }

  public static class Builder extends AbstractBuilder<Builder> {
    @Override
    public Builder self() {
      return this;
    }

    public ChildUser build() {
      return new ChildUser(this);
    }
  }

  public abstract static class AbstractBuilder<T> extends User.AbstractBuilder<T> {
    protected boolean flying;

    /**
     * Copies all values from the user domain object into the user builder.
     *
     * @param user domain object
     * @return builder object
     */
    public T withValues(final ChildUser user) {
      super.withValues(user);
      if (user instanceof ChildUser) {
        final ChildUser bird = user;
        this.flying = bird.flying;
      }
      return self();
    }

    public T isFlying(final boolean flying) {
      this.flying = flying;
      return self();
    }
  }
}
