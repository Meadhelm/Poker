package com.nogo.poker.web;

import com.nogo.api.annotation.Document;
import com.nogo.api.annotation.Endpoint;
import com.nogo.poker.domain.RequestContext;
import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.service.UserService;
import com.nogo.poker.web.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

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
      @Valid @RequestBody final UserDto userDto) {
    final User user = userDto.toDomain();
    return userService.createUser(user);
  }

  /**
   * Retrieves a user resource by GUID.
   *
   * @param requestContext The context container for the request.
   * @param id The GUID of the user resource.
   * @return The hydrated USER resource object.
   */
  @Endpoint(name = "RETRIEVE.USER")
  @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public UserDto retrieve(final RequestContext requestContext,
      @PathVariable("id") final String id) {
    return userService.retrieveUser(id).toDto();
  }

  /**
   * Retrieves a user resource by GUID.
   *
   * @param requestContext The context container for the request.
   * @param firstName The GUID of the user resource.
   * @return The hydrated USER resource object.
   */
  @Endpoint(name = "FIND.USERS")
  @RequestMapping(value = "/v1/user", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public List<UserDto> findByExample(final RequestContext requestContext,
      @RequestParam("firstName") final String firstName) {
    final Collection<User> users = userService.findByExample(firstName);

    final List<UserDto> userDtos = new ArrayList<>();
    for (final User user : users) {
      userDtos.add(user.toDto());
    }
    return userDtos;
  }
}
