package com.nogo.poker.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The requested resource was not found")
public class ResourceNotFoundException extends PokerException {
  private static final long serialVersionUID = -4141337599702861228L;

}
