package com.videogamerental.domain.factory;

import com.videogamerental.domain.Game;
import com.videogamerental.domain.GameNewRelease;
import com.videogamerental.domain.GameStandard;
import com.videogamerental.infrastructure.database.entities.GameEntity;
import com.videogamerental.infrastructure.database.entities.OrderItemEntity;

public enum GameFactory {
  N {
    @Override
    public Game createGame(OrderItemEntity obj) {
      return GameNewRelease.builder()
          .id(obj.getGame().getId())
          .type(obj.getGame().getProperties().getType())
          .name(obj.getGame().getName())
          .rentDays(obj.getGame().getProperties().getRentDays())
          .rentPrice(obj.getGame().getProperties().getRentPrice())
          .daysRented(obj.getDaysRented())
          .build();
    }

    @Override
    public Game createGame(GameEntity obj) {
      return GameNewRelease.builder()
          .id(obj.getId())
          .type(obj.getProperties().getType())
          .name(obj.getName())
          .rentDays(obj.getProperties().getRentDays())
          .rentPrice(obj.getProperties().getRentPrice())
          .build();
    }
  },
  S {
    @Override
    public Game createGame(OrderItemEntity obj) {
      return GameStandard.builder()
          .id(obj.getGame().getId())
          .type(obj.getGame().getProperties().getType())
          .name(obj.getGame().getName())
          .rentDays(obj.getGame().getProperties().getRentDays())
          .rentPrice(obj.getGame().getProperties().getRentPrice())
          .daysRented(obj.getDaysRented())
          .build();
    }

    @Override
    public Game createGame(GameEntity obj) {
      return GameStandard.builder()
          .id(obj.getId())
          .type(obj.getProperties().getType())
          .name(obj.getName())
          .rentDays(obj.getProperties().getRentDays())
          .rentPrice(obj.getProperties().getRentPrice())
          .build();
    }
  },
  C {
    @Override
    public Game createGame(OrderItemEntity obj) {
      return GameFactory.S.createGame(obj);
    }

    @Override
    public Game createGame(GameEntity obj) {
      return GameFactory.S.createGame(obj);
    }
  };

  public abstract Game createGame(OrderItemEntity obj);

  public abstract Game createGame(GameEntity obj);
}
