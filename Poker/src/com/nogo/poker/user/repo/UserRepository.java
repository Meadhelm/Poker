package com.nogo.poker.user.repo;

import com.nogo.poker.user.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  public String createUser(final User user) {
    return "1";
  }

}
