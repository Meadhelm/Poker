package com.nogo.poker.user.service;

import com.nogo.poker.common.validation.ValidationService;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.repo.UserRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private ValidationService validator;

  @Autowired
  private UserRepository userRepo;

  public String createUser(final User newUser) {
    final String violations = validator.validate(newUser);
    if (StringUtils.isNotBlank(violations)) {
      throw new IllegalArgumentException(violations);
    }
    return userRepo.createUser(newUser);
  }

  public User retrieveUser(final String id) {
    return userRepo.getUser(new Long(id));
  }
}
