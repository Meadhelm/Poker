package com.nogo.poker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,
    reason = "The requested is in conflict with a pervious request or resource")
public class ResourceConflictException extends PokerException {
  private static final long serialVersionUID = -4141337599702861228L;

}
