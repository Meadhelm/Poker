package com.nogo.poker.web;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.nogo.api.annotation.Document;
import com.nogo.api.annotation.Endpoint;
import com.nogo.poker.dao.ResourceDao;
import com.nogo.poker.domain.LoginRequest;
import com.nogo.poker.domain.Pagination;
import com.nogo.poker.domain.RequestContext;
import com.nogo.poker.domain.User;
import com.nogo.poker.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import javax.validation.Valid;

@Document
@Controller
public class UserController {

  @Autowired
  private ResourceDao resourceDao;

  @Endpoint(name = "LOGIN")
  @RequestMapping(value = "/v1/user/login", method = POST, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  @ResponseBody
  public String login(final RequestContext reqCtx, @Valid @RequestBody final LoginRequest user) {
    return "ok";
  }

  @Endpoint(name = "CREATE.USER")
  @RequestMapping(value = "/v1/users", method = POST, consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  @ResponseBody
  public String createUser(final RequestContext reqCtx, @Valid @RequestBody final User user) {
    return resourceDao.save(user);
  }

  /**
   * This returns cool users.
   *
   * @param reqCtx The request context
   * @return The user record.
   */
  @Endpoint(name = "RETRIEVE.USERS")
  @RequestMapping(value = "/v1/users", method = GET, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @ResponseBody
  public List<User> getUser(final RequestContext reqCtx, final Pagination pagination) {
    return resourceDao.findAll(pagination);
  }

  /**
   * This returns cool users.
   *
   * @param reqCtx The request context
   * @param id The user id used to retrieve the full user record.
   * @return The user record.
   */
  @Endpoint(name = "RETRIEVE.USER")
  @RequestMapping(value = "/v1/users/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  @ResponseBody
  public User getUser(final RequestContext reqCtx, @PathVariable final String id) {
    final User user = resourceDao.findById(id);
    if (user == null) {
      throw new ResourceNotFoundException();
    }
    return user;
  }
}
