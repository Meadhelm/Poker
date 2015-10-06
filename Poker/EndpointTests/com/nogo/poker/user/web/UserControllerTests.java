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
import com.nogo.poker.user.service.UserService;
import com.nogo.poker.web.UserController;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    request.add("\"firstName\" : \"Chad\",");
    request.add("\"lastName\" : \"Nogosek\",");
    request.add("\"email\" : \"chadnogosek@poker.com\",");
    request.add("\"type\" : \"user\",");
    request.add("\"effectiveDate\" : \"3015-10-02\",");
    request.add("\"endDate\" : \"3015-10-04\"");
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
    final User user = new User.Builder().withId("1").withFirstName("Chad").withLastName("Nogosek")
        .withEmail("chadnogosek@poker.com").withCreatedDate(DATE_TIME).withModifiedDate(DATE_TIME)
        .withEndDate(DATE_TIME).withEffectiveDate(DATE_TIME).build();

    when(mockUserService.findById(anyString())).thenReturn(user);

    // test, verify
    this.mockMvc
        .perform(
            get("/v1/user/1").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().isOk())
        .andExpect(content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("$.id").value("1")).andExpect(jsonPath("$.firstName").value("Chad"))
        .andExpect(jsonPath("$.lastName").value("Nogosek"))
        .andExpect(jsonPath("$.email").value("chadnogosek@poker.com"))
        .andExpect(jsonPath("$.createdDate").value(DATE_STRING))
        .andExpect(jsonPath("$.modifiedDate").value(DATE_STRING))
        .andExpect(jsonPath("$.effectiveDate").value(DATE_STRING))
        .andExpect(jsonPath("$.endDate").value(DATE_STRING));
  }
}
