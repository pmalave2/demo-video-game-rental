package com.videogamerental.domain.service;

import com.videogamerental.application.request.GamesRentRequest;
import com.videogamerental.application.request.GamesReturnRequest;
import com.videogamerental.application.response.GamesRentResponse;
import com.videogamerental.application.response.GamesReturnResponse;
import java.util.List;
import java.util.UUID;

public interface OrderDomainService {
  List<GamesRentResponse> getRentalOrders();

  GamesRentResponse createRentalOrder(GamesRentRequest obj);

  GamesRentResponse getRentalOrder(UUID id);

  GamesReturnResponse calculateGameReturn(UUID id, GamesReturnRequest obj);
}
