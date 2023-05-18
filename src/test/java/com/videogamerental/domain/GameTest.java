package com.videogamerental.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class GameTest {
  @Test
  void giveNewReleaseGame_whenCalculateRentPrice_thenRetunEightPrice() {
    // Given
    Game game =
        GameNewRelease.builder().id(null).type(GameType.N).rentDays(0).rentPrice(4.0).daysRented(2).build();

    // When
    Double calculatedPrice = game.calculateRentPrice();

    // Then
    assertEquals(8.0, calculatedPrice);
  }

  @Test
  void giveStandarGame_whenCalculateRentPriceForThreeDays_thenRetunThreeAsPrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.S).rentDays(3).rentPrice(3.0).daysRented(3).build();

    // When
    Double calculatedPrice = game.calculateRentPrice();

    // Then
    assertEquals(3.0, calculatedPrice);
  }

  @Test
  void giveStandarGame_whenCalculateRentPriceForFiveDays_thenRetunNineAsPrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.S).rentDays(3).rentPrice(3.0).daysRented(5).build();

    // When
    Double calculatedPrice = game.calculateRentPrice();

    // Then
    assertEquals(9.0, calculatedPrice);
  }

  @Test
  void giveClassicGame_whenCalculateRentPriceForFiveDays_thenRetunThreeAsPrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.C).rentDays(5).rentPrice(3.0).daysRented(5).build();

    // When
    Double calculatedPrice = game.calculateRentPrice();

    // Then
    assertEquals(3.0, calculatedPrice);
  }

  @Test
  void giveClassicGame_whenCalculateRentPriceForTenDays_thenRetunEighteenAsPrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.C).rentDays(5).rentPrice(3.0).daysRented(10).build();

    // When
    Double calculatedPrice = game.calculateRentPrice();

    // Then
    assertEquals(18.0, calculatedPrice);
  }

  @Test
  void giveClassicGame_whenCalculateRentOverdueForZeroDays_thenRetunZeroAsOverduePrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.C).rentDays(5).rentPrice(3.0).daysRented(6).build();

    // When
    var calculatedPrice = game.calculateRentOverdue(LocalDate.now().minusDays(6));

    // Then
    assertEquals(0.0, calculatedPrice.price());
    assertEquals(0l, calculatedPrice.days());
  }

  @Test
  void giveClassicGame_whenCalculateRentOverdueForTwoDays_thenRetunSixAsOverduePrice() {
    // Given
    Game game =
        GameStandard.builder().id(null).type(GameType.C).rentDays(5).rentPrice(3.0).daysRented(6).build();

    // When
    var calculatedPrice = game.calculateRentOverdue(LocalDate.now().minusDays(6 + 2));

    // Then
    assertEquals(6.0, calculatedPrice.price());
    assertEquals(2l, calculatedPrice.days());
  }
}
