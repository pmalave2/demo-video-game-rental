package com.videogamerental.domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@SuperBuilder
public abstract class Game {
  private UUID id;
  private GameType type;
  private String name;
  private Integer rentDays;
  private Double rentPrice;

  private Integer daysRented;

  public abstract Double calculateRentPrice();

  public RentOverdue calculateRentOverdue(@Nonnull LocalDate rentedDate) {
    var now = LocalDate.now();
    var returnDate = rentedDate.plusDays(this.daysRented);

    var diff = returnDate.until(now, ChronoUnit.DAYS);

    if (diff > 0) return new RentOverdue(diff * rentPrice, diff);
    else return new RentOverdue(0.0, 0l);
  }

  public record RentOverdue(Double price, Long days) {}
}
