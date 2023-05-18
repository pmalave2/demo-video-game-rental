package com.videogamerental.utils;

import com.videogamerental.application.request.GamesItemRequest;
import com.videogamerental.application.request.GamesRentRequest;
import com.videogamerental.application.request.GamesReturnRequest;
import com.videogamerental.domain.Game;
import com.videogamerental.domain.GameNewRelease;
import com.videogamerental.domain.GameStandard;
import com.videogamerental.domain.GameType;
import com.videogamerental.domain.Order;
import com.videogamerental.infrastructure.database.entities.GameEntity;
import com.videogamerental.infrastructure.database.entities.GamePropertiesEntity;
import com.videogamerental.infrastructure.database.entities.OrderEntity;
import com.videogamerental.infrastructure.database.entities.OrderItemEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ObjectsGenerator {
  public static List<Game> gamesDomain() {
    return List.of(
        GameNewRelease.builder()
            .id(UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539d"))
            .type(GameType.N)
            .name("LoZ: TotK")
            .rentDays(3)
            .rentPrice(4.0)
            .daysRented(5)
            .build(),
        GameStandard.builder()
            .id(UUID.fromString("f1f4d6fd-367d-4a3e-bd0c-17a060516c57"))
            .type(GameType.S)
            .name("LoZ: BotW")
            .rentDays(3)
            .rentPrice(3.0)
            .daysRented(5)
            .build(),
        GameStandard.builder()
            .id(UUID.fromString("6bee6d79-9e64-4943-89da-d5b7e5811fb4"))
            .type(GameType.C)
            .name("Super Mario Odyssey")
            .rentDays(5)
            .rentPrice(3.0)
            .daysRented(5)
            .build());
  }

  public static List<GameEntity> gameEntities() {
    return List.of(
        GameEntity.builder()
            .id(UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539d"))
            .name("LoZ: TotK")
            .properties(
                GamePropertiesEntity.builder().type(GameType.N).rentDays(3).rentPrice(4.0).build())
            .build(),
        GameEntity.builder()
            .id(UUID.fromString("f1f4d6fd-367d-4a3e-bd0c-17a060516c57"))
            .name("LoZ: BotW")
            .properties(
                GamePropertiesEntity.builder().type(GameType.S).rentDays(3).rentPrice(3.0).build())
            .build(),
        GameEntity.builder()
            .id(UUID.fromString("6bee6d79-9e64-4943-89da-d5b7e5811fb4"))
            .name("Super Mario Odyssey")
            .properties(
                GamePropertiesEntity.builder().type(GameType.C).rentDays(5).rentPrice(3.0).build())
            .build());
  }

  public static OrderEntity orderEntity() {
    return OrderEntity.builder()
        .id(UUID.fromString("a42c995d-57ac-4eac-a6de-be1bae5ddf17"))
        .date(LocalDate.now())
        .idCustomer(UUID.fromString("c04cd217-0ae3-4529-ae04-ab6f2288fcbc"))
        .items(orderItemEntities())
        .build();
  }

  public static Set<OrderItemEntity> orderItemEntities() {
    return Set.of(
        OrderItemEntity.builder()
            .id(UUID.randomUUID())
            .daysRented(5)
            .game(gameEntities().get(0))
            .build());
  }

  public static Order orderDomain() {
    return Order.builder()
        .id(UUID.fromString("75822aa6-1d03-4982-b119-b7998b8b6a5f"))
        .date(LocalDate.now())
        .idCustomer(UUID.fromString("01501d46-963e-481b-8e7a-484ad741d4a2"))
        .games(gamesDomain())
        .build();
  }

  public static GamesReturnRequest gamesReturnRequest() {
    return GamesReturnRequest.builder()
        .gamesIds(List.of("72a759bb-17c7-4151-b342-84d5c535539d"))
        .build();
  }

  public static GamesRentRequest gamesRentRequest() {
    return GamesRentRequest.builder()
        .idCustomer("01501d46-963e-481b-8e7a-484ad741d4a2")
        .games(
            List.of(
                GamesItemRequest.builder()
                    .idGame("72a759bb-17c7-4151-b342-84d5c535539d")
                    .daysRented(5)
                    .build(),
                GamesItemRequest.builder()
                    .idGame("f1f4d6fd-367d-4a3e-bd0c-17a060516c57")
                    .daysRented(5)
                    .build()))
        .build();
  }
}
