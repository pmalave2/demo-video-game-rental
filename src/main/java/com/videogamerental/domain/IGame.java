package com.videogamerental.domain;

public interface IGame {
  Double calculateRentPrice();
  Double calculateRentOverdue(Integer days);
}
