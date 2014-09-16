package com.nogo.poker.user.repo;

import org.springframework.stereotype.Repository;

import com.nogo.poker.user.domain.User;

@Repository
public class UserRepository {

  public String createUser(final User user) {
    return "1";
  }

}
