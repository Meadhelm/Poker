package com.nogo.poker.user.domain;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nogo.poker.common.validation.ValidationService;
import com.nogo.poker.user.repo.UserRepository;

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
    // throw new ResourceNotFoundException();
    return new User.Builder().withName("Chad")
        .withEmail("chadnogosek@gmail.com")
        .withCreatedDate(DateTime.now())
        .withModifiedDate(DateTime.now())
        .build();
  }
}
