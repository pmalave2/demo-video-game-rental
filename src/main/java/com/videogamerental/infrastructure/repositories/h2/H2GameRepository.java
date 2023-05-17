package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.infrastructure.database.entities.GameEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2GameRepository extends JpaRepository<GameEntity, UUID> {
  @EntityGraph(value = "Game.properties")
  List<GameEntity> findAll();

  @EntityGraph(value = "Game.properties")
  Optional<GameEntity> findById(UUID id);
}
