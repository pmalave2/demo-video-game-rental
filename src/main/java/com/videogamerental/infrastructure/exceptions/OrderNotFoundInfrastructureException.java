package com.videogamerental.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Order Not Found")
public class OrderNotFoundInfrastructureException extends RuntimeException {}
