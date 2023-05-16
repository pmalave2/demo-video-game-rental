package com.videogamerental.domain;

public enum GameType {
  N("New release"),
  S("Standard game"),
  C("Classic game");

  private String description;

  private GameType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
