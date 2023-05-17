package com.videogamerental.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Game Not Found")
public class GameNotFoundDomainException extends RuntimeException {
  public GameNotFoundDomainException(String message) {
    super(message);
  }
}
