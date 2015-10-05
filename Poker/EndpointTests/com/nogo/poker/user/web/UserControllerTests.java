package com.nogo.poker.user.web;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.domain.UserService;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTests {
  private static final DateTime DATE_TIME = DateTime.now();
  private static final String DATE_STRING = DATE_TIME.toString();

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService mockUserService;

  private MockMvc mockMvc;

  /**
   * Initialization of context needed to test the UserController endpoints.
   *
   */
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void createUser() throws Exception {
    // setup
    when(mockUserService.createUser(any(User.class))).thenReturn("1");

    final List<String> request = new ArrayList<>();
    request.add("{");
    request.add("\"name\" : \"Chad\",");
    request.add("\"email\" : \"chadnogosek@poker.com\"");
    request.add("}");

    final String requestJson = StringUtils.join(request, "");

    // test, verify
    final MvcResult result = this.mockMvc
        .perform(post("/v1/user").content(requestJson)
            .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().isCreated()).andReturn();

    assertThat(result.getResponse().getContentAsString()).isEqualTo("1");
  }

  @Test
  public void getUser() throws Exception {
    // setup
    final User mockUser = Mockito.mock(User.class);
    when(mockUser.getName()).thenReturn("Chad");
    when(mockUser.getEmail()).thenReturn("chadnogosek@poker.com");
    when(mockUser.getCreatedDate()).thenReturn(DATE_TIME);
    when(mockUser.getModifiedDate()).thenReturn(DATE_TIME);

    when(mockUserService.retrieveUser(anyString())).thenReturn(mockUser);

    // test, verify
    this.mockMvc
        .perform(
            get("/v1/user/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.name").value("Chad"))
        .andExpect(jsonPath("$.email").value("chadnogosek@poker.com"))
        .andExpect(jsonPath("$.createdDate").value(DATE_STRING))
        .andExpect(jsonPath("$.modifiedDate").value(DATE_STRING));
  }
}
