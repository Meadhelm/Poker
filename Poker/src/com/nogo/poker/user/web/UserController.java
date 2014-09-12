package com.nogo.poker.user.web;

import javax.validation.Valid;

import org.joda.time.DateTime;
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

import com.nogo.poker.user.domain.User;
import com.nogo.poker.user.domain.UserService;

@Controller
public final class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String createUser(@Valid @RequestBody final UserDto user)
    {
        return userService.createUser(user.toDomainObject());
    }

    @RequestMapping(value = "/v1/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto retrieve(@PathVariable final String id)
    {
        final User user = new User.Builder().withName("chad").withCreatedDate(DateTime.now())
                .withModifiedDate(DateTime.now()).build();

        return new UserDto(user);
    }
}