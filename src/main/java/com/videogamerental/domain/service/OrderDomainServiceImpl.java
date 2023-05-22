package com.videogamerental.domain.service;

import com.videogamerental.application.request.GamesRentRequest;
import com.videogamerental.application.request.GamesReturnRequest;
import com.videogamerental.application.response.GamesItemResponse;
import com.videogamerental.application.response.GamesItemReturnResponse;
import com.videogamerental.application.response.GamesRentResponse;
import com.videogamerental.application.response.GamesReturnResponse;
import com.videogamerental.domain.Game;
import com.videogamerental.domain.Order;
import com.videogamerental.domain.repository.GameReadById;
import com.videogamerental.domain.repository.OrderRead;
import com.videogamerental.domain.repository.OrderReadAll;
import com.videogamerental.domain.repository.OrderSave;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderDomainServiceImpl implements OrderDomainService {
  private final GameReadById gameReadById;
  private final OrderSave orderSave;
  private final OrderRead orderRead;
  private final OrderReadAll orderReadAll;

  @Override
  public GamesRentResponse createRentalOrder(GamesRentRequest obj) {
    var order = createOrder(obj);
    orderSave.save(order);

    return calculateRent(order);
  }

  private Order createOrder(GamesRentRequest obj) {
    var order =
        Order.builder()
            .id(UUID.randomUUID())
            .date(LocalDate.now())
            .idCustomer(UUID.fromString(obj.getIdCustomer()))
            .build();

    order.setGames(getGames(obj));

    return order;
  }

  private List<Game> getGames(GamesRentRequest obj) {
    var games = new ArrayList<Game>();

    for (var gameRequest : obj.getGames()) {
      var gameDomain = gameReadById.findById(UUID.fromString(gameRequest.getIdGame()));
      gameDomain.setDaysRented(gameRequest.getDaysRented());
      games.add(gameDomain);
    }

    return games;
  }

  private GamesRentResponse calculateRent(Order order) {
    var games = new ArrayList<GamesItemResponse>();
    var total = 0.0;

    for (var game : order.getGames()) {
      var price = game.calculateRentPrice();

      var gameResponse =
          GamesItemResponse.builder()
              .id(game.getId().toString())
              .name(game.getName())
              .type(game.getType().getDescription())
              .daysRented(game.getDaysRented())
              .price(price)
              .build();

      games.add(gameResponse);
      total += gameResponse.getPrice();
    }

    return GamesRentResponse.builder()
        .idOrder(order.getId().toString())
        .games(games)
        .total(total)
        .build();
  }

  @Override
  public GamesRentResponse getRentalOrder(UUID id) {
    var order = orderRead.findById(id);

    return calculateRent(order);
  }

  @Override
  public GamesReturnResponse calculateGameReturn(UUID id, GamesReturnRequest obj) {
    var order = orderRead.findById(id);

    var gamesSelected = new ArrayList<Game>();
    for (var game : order.getGames()) {
      if (obj.getGamesIds().contains(game.getId().toString())) gamesSelected.add(game);
    }

    return calculateReturn(gamesSelected, order.getDate());
  }

  private GamesReturnResponse calculateReturn(List<Game> games, LocalDate rentDate) {
    var gamesResponse = new ArrayList<GamesItemReturnResponse>();
    var total = 0.0;

    for (var game : games) {
      var rentOverdue = game.calculateRentOverdue(rentDate);

      var gameReturnResponse =
          GamesItemReturnResponse.builder()
              .id(game.getId().toString())
              .name(game.getName())
              .type(game.getType().getDescription())
              .daysRented(game.getDaysRented())
              .extraDays(rentOverdue.days())
              .priceExtraDays(rentOverdue.price())
              .build();

      gamesResponse.add(gameReturnResponse);
      total += gameReturnResponse.getPriceExtraDays();
    }

    return GamesReturnResponse.builder()
        .rentDate(rentDate)
        .games(gamesResponse)
        .total(total)
        .build();
  }

  @Override
  public List<GamesRentResponse> getRentalOrders() {
    return orderReadAll.findAll().stream().map(this::calculateRent).toList();
  }
}
