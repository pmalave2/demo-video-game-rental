package com.videogamerental.infrastructure.repositories.h2;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamerental.infrastructure.database.entities.GameEntity;

public interface H2GameRepository extends JpaRepository<GameEntity, UUID> {
  @EntityGraph(value = "Game.properties")
  List<GameEntity> findAll();
}
