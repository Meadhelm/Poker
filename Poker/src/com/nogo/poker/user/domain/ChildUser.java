package com.nogo.poker.user.domain;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;

public class ChildUser extends User {

  public ChildUser(final AbstractBuilder<?> builder) {
    super(builder);
    this.flying = builder.flying;
  }

  private final String flying;

  public String isFlying() {
    return flying;
  }

  @Override
  public String toString() {
    final List<String> print = new ArrayList<>();
    print.add(super.toString());
    print.add("flying: " + flying);
    return join(print, ", ");
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

  abstract static class AbstractBuilder<T> extends User.AbstractBuilder<T> {
    protected String flying;

    public T isFlying(final String flying) {
      this.flying = flying;
      return self();
    }

    @Override
    public T withValues(final User animal) {
      super.withValues(animal);
      if (animal instanceof ChildUser) {
        final ChildUser bird = (ChildUser) animal;
        this.flying = bird.flying;
      }
      return self();
    }
  }
}
