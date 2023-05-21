package com.videogamerental.domain;

import com.videogamerental.domain.factory.GameFactory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GameType {
  N("New release") {
    @Override
    public GameFactory factory() {
      return GameFactory.N;
    }
  },
  S("Standard game") {
    @Override
    public GameFactory factory() {
      return GameFactory.S;
    }
  },
  C("Classic game") {
    @Override
    public GameFactory factory() {
      return GameFactory.C;
    }
  };

  private String description;

  public abstract GameFactory factory();
}
