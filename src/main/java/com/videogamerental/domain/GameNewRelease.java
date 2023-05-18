package com.videogamerental.domain;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class GameNewRelease extends Game {
  public Double calculateRentPrice() {
    return this.getDaysRented() * this.getRentPrice();
  }
}
