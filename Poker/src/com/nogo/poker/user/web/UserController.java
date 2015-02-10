package com.nogo.poker.user.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nogo.api.annotation.Document;
import com.nogo.api.annotation.Endpoint;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.domain.UserService;
import com.nogo.poker.web.RequestContext;

@Document
@Controller
public class UserController {
  @Autowired
  private UserService userService;

  @Endpoint(name = "CREATE.USER")
  @RequestMapping(value = "/v1/user", method = RequestMethod.POST,
  consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  public String createUser(final RequestContext requestContext,
      @Valid @RequestBody final UserDto user) {
    return userService.createUser(user.toDomainObject());
  }

  @Endpoint(name = "RETRIEVE.USER")
  @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET,
  produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UserDto retrieve(final RequestContext requestContext, @PathVariable("id") final String id) {
    final User user = userService.retrieveUser(id);

    return new UserDto(user);
  }
}
