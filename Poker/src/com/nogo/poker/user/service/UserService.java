package com.nogo.poker.user.service;

import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.repository.UserRepository;
import com.nogo.poker.validation.ValidationService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private ValidationService validator;

  @Autowired
  private UserRepository userRepo;

  /**
   * Creates a new user
   *
   * @param newUser The user object to create.
   * @return The resource identifier
   */
  public String createUser(final User newUser) {
    final String violations = validator.validate(newUser);
    if (StringUtils.isNotBlank(violations)) {
      throw new IllegalArgumentException(violations);
    }

    return userRepo.createUser(newUser);
  }

  public User retrieveUser(final String id) {
    return userRepo.getUser(id);
  }
}
