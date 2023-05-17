package com.videogamerental.domain.service;

import com.videogamerental.application.request.GamesRentRequest;
import com.videogamerental.application.request.GamesReturnRequest;
import com.videogamerental.application.response.GamesRentResponse;
import com.videogamerental.application.response.GamesReturnResponse;
import java.util.UUID;

public interface OrderDomainService {
  GamesRentResponse createRentalOrder(GamesRentRequest obj);

  GamesRentResponse getRentalOrder(UUID id);

  GamesReturnResponse calculateGameReturn(UUID id, GamesReturnRequest obj);
}
