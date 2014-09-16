package com.nogo.poker;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.nogo.poker.common.Validator;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.domain.UserService;
import com.nogo.poker.user.repo.UserRepository;

public class UserServiceTests {
  private static final String USER_ID = "userId";
  private static final String VALIDATION_FAILURE_MSG = "constraints violated!";

  @InjectMocks
  private UserService userService;

  @Mock
  private Validator mockValidator;

  @Mock
  private UserRepository mockUserRepo;


  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createUser() {
    // setup
    when(mockValidator.validate(any(User.class))).thenReturn(StringUtils.EMPTY);
    when(mockUserRepo.createUser(any(User.class))).thenReturn(USER_ID);

    final User mockUser = Mockito.mock(User.class);

    // test
    final String result = userService.createUser(mockUser);

    // verify
    assertThat(result).isEqualTo(USER_ID);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createUser_validationFail() {
    // setup
    when(mockUserRepo.createUser(any(User.class))).thenReturn(USER_ID);

    final User mockUser = Mockito.mock(User.class);
    when(mockValidator.validate(any(User.class))).thenReturn(VALIDATION_FAILURE_MSG);

    // test
    userService.createUser(mockUser);

    // verify
    verify(mockUserRepo, never()).createUser(any(User.class));
  }
}
