package com.videogamerental.application.rest;

import com.videogamerental.application.request.GamesRentRequest;
import com.videogamerental.application.request.GamesReturnRequest;
import com.videogamerental.application.response.GamesRentResponse;
import com.videogamerental.application.response.GamesReturnResponse;
import com.videogamerental.domain.service.OrderDomainService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/rents")
public class RentController {
  private OrderDomainService orderDomainService;

  @PostMapping
  public GamesRentResponse rent(@RequestBody @Validated GamesRentRequest gamesRentRequest) {
    return orderDomainService.createRentalOrder(gamesRentRequest);
  }

  @GetMapping(path = "/{id}")
  public GamesRentResponse getOrder(@PathVariable(required = true) UUID id) {
    return orderDomainService.getRentalOrder(id);
  }

  @PostMapping(path = "/{id}/return")
  public GamesReturnResponse gameReturn(
      @PathVariable(required = true) UUID id, @RequestBody @Validated GamesReturnRequest obj) {
    return orderDomainService.calculateGameReturn(id, obj);
  }
}
