package com.nogo.poker.user.domain;

import static org.apache.commons.lang3.StringUtils.join;

import java.util.ArrayList;
import java.util.List;

public class GrandChildUser extends ChildUser {

  public GrandChildUser(final AbstractBuilder<?> builder) {
    super(builder);
    rank = builder.rank;
  }

  private final String rank;

  public String getRank() {
    return rank;
  }

  @Override
  public String toString() {
    final List<String> print = new ArrayList<>();
    print.add(super.toString());
    print.add("rank: " + rank);
    return join(print, ", ");
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

  abstract static class AbstractBuilder<T> extends ChildUser.AbstractBuilder<T> {
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
