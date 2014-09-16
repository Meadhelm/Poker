package com.nogo.poker.user.domain;

public class GrandChildUser extends ChildUser {
  private String rank;

  public String getRank() {
    return rank;
  }

  @Override
  public String toString() {
    return rank + super.toString();
  }

  public GrandChildUser() {

  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public GrandChildUser(final AbstractBuilder<Builder> builder) {
    super((AbstractBuilder) builder);
    rank = builder.rank;
  }

  public static Builder Builder() {
    return new Builder();
  }

  public static class Builder extends AbstractBuilder<Builder> {
    @Override
    public Builder self() {
      return this;
    }

    public GrandChildUser build() {
      return new GrandChildUser(this);
    }
  }

  static abstract class AbstractBuilder<T> extends ChildUser.AbstractBuilder<T> {
    protected String rank;

    public T withRank(final String rank) {
      this.rank = rank;
      return self();
    }

    @Override
    public T withValues(final User userDto) {
      super.withValues(userDto);
      if (userDto instanceof GrandChildUser) {
        final GrandChildUser superCoolUserDto = (GrandChildUser) userDto;
        this.rank = superCoolUserDto.getRank();

      }
      return self();
    }
  }
}
