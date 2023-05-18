package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.domain.Game;
import com.videogamerental.domain.factory.GameFactory;
import com.videogamerental.domain.repository.GameRead;
import com.videogamerental.domain.repository.GameReadById;
import com.videogamerental.infrastructure.database.entities.GameEntity;
import com.videogamerental.infrastructure.exceptions.GameNotFoundInfrastructureException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GamesAdapter implements GameReadById, GameRead {
  private final H2GameRepository h2GameRepository;

  @Override
  public Game findById(UUID id) {
    var entity = h2GameRepository.findById(id);

    if (!entity.isPresent())
      throw new GameNotFoundInfrastructureException(String.format("Game %s not found", id));

    return entityToDomain(entity.get());
  }

  @Override
  public List<Game> findAll() {
    return h2GameRepository.findAll().stream().map(GamesAdapter::entityToDomain).toList();
  }

  private static Game entityToDomain(GameEntity entity) {
    return GameFactory.valueOf(entity.getProperties().getType().toString()).createGame(entity);
  }
}
