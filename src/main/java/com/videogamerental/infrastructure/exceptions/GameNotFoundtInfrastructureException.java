package com.videogamerental.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Game Not Found")
public class GameNotFoundtInfrastructureException extends RuntimeException {
  public GameNotFoundtInfrastructureException(String message) {
    super(message);
  }
}
