package com.videogamerental.domain;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GameStandard extends Game {
  public Double calculateRentPrice() {
    Double calculatedPrice = this.getRentPrice();

    if (this.getDaysRented() > this.getRentDays()) {
      calculatedPrice += (this.getDaysRented() - this.getRentDays()) * this.getRentPrice();
    }

    return calculatedPrice;
  }
}
