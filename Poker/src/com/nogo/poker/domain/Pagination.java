package com.nogo.poker.domain;

import static java.lang.Integer.valueOf;

public class Pagination {

  private final int start;
  private final int stop;

  public Pagination(final int start, final int stop) {
    this.start = start;
    this.stop = stop;
  }

  public Pagination(final String start, final String stop) {
    this.start = start == null ? 100 : valueOf(start);
    this.stop = stop == null ? 100 : valueOf(stop);
  }

  public int getStart() {
    return start;
  }

  public int getStop() {
    return stop;
  }

}
