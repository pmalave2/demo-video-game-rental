package com.videogamerental.domain.factory;

import com.videogamerental.domain.Game;
import com.videogamerental.domain.GameNewRelease;
import com.videogamerental.domain.GameStandard;
import com.videogamerental.infrastructure.database.entities.GameEntity;
import com.videogamerental.infrastructure.database.entities.OrderItemEntity;

public enum GameFactory {
  N {
    @Override
    public Game createDomainGame(OrderItemEntity obj) {
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
    public Game createDomainGame(GameEntity obj) {
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
    public Game createDomainGame(OrderItemEntity obj) {
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
    public Game createDomainGame(GameEntity obj) {
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
    public Game createDomainGame(OrderItemEntity obj) {
      return GameFactory.S.createDomainGame(obj);
    }

    @Override
    public Game createDomainGame(GameEntity obj) {
      return GameFactory.S.createDomainGame(obj);
    }
  };

  public abstract Game createDomainGame(OrderItemEntity obj);

  public abstract Game createDomainGame(GameEntity obj);
}
