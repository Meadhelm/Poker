package com.nogo.poker.user.domain;

public class ChildUser extends User {
  private String flying;

  public String isFlying() {
    return flying;
  }

  public ChildUser() {

  }

  @Override
  public String toString() {
    return flying + super.toString();
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public ChildUser(final AbstractBuilder<Builder> builder) {
    super((AbstractBuilder) builder);
    this.flying = builder.flying;
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

  static abstract class AbstractBuilder<T> extends User.AbstractBuilder<T> {
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
