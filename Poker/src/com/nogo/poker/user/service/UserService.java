package com.nogo.poker.user.service;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import com.nogo.poker.exception.ResourceNotFoundException;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.repository.UserRepository;
import com.nogo.poker.validation.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
    if (isNotBlank(violations)) {
      throw new IllegalArgumentException(violations);
    }

    return userRepo.createUser(newUser);
  }

  /**
   * Extends an existing user.
   *
   * @param newUser user to extend
   * @return id of new user
   */
  public String extendUser(final String id, final User newUser) {
    final String violations = validator.validate(newUser);
    if (isNotBlank(violations)) {
      throw new IllegalArgumentException(violations);
    }

    final User existingUser = findById(id);
    if (existingUser == null) {
      throw new ResourceNotFoundException();
    }
    if (existingUser.getType().equals(newUser.getType())) {
      throw new ResourceNotFoundException();
    }
    return userRepo.updateUser(existingUser, newUser);
  }

  public Collection<User> findByExample(final User example) {
    return userRepo.findByExample(example);
  }

  public User findById(final String id) {
    return userRepo.findById(id);
  }
}
