package com.videogamerental.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameType {
  N("New release"),
  S("Standard game"),
  C("Classic game");

  private String description;
}
