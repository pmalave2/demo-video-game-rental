package com.videogamerental.domain;

import jakarta.annotation.Nonnull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Game {
  private UUID id;
  private GameType type;
  private String name;
  private Integer rentDays;
  private Double rentPrice;

  private Integer daysRented;

  public Double calculateRentPrice() {
    Double calculatedPrice;

    if (GameType.N.equals(type)) {
      calculatedPrice = this.daysRented * this.rentPrice;
    } else {
      calculatedPrice = this.rentPrice;
      if (daysRented > rentDays) {
        calculatedPrice += (daysRented - rentDays) * rentPrice;
      }
    }

    return calculatedPrice;
  }

  public RentOverdue calculateRentOverdue(@Nonnull LocalDate rentedDate) {
    var now = LocalDate.now();
    var returnDate = rentedDate.plusDays(this.daysRented);

    var diff = returnDate.until(now, ChronoUnit.DAYS);

    if (diff > 0) return new RentOverdue(diff * rentPrice, diff);
    else return new RentOverdue(0.0, 0l);
  }

  public record RentOverdue(Double price, Long days) {}
}
