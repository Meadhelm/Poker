package com.nogo.poker.user.web;

import static org.fest.assertions.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nogo.poker.web.UserController;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

public class UserControllerTests {

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;

  /**
   * Initialization of context needed to test the UserController endpoints.
   *
   */
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);

    mockMvc = MockMvcBuilders.standaloneSetup(userController)
        .build();
  }

  @Test
  public void createUser() throws Exception {
    // setup

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
    final MvcResult result = this.mockMvc.perform(post("/v1/user").content(requestJson)
        .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")))
        .andExpect(status().isCreated())
        .andReturn();

    assertThat(result.getResponse()
        .getContentAsString()).isEqualTo("1");
  }
}
