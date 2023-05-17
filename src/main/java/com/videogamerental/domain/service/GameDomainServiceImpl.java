package com.videogamerental.domain.service;

import com.videogamerental.application.response.GamesItemListResponse;
import com.videogamerental.domain.repository.GameRead;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GameDomainServiceImpl implements GameDomainService {
  private final GameRead gameRead;

  @Override
  public List<GamesItemListResponse> findAllGames() {
    return gameRead.findAll().stream()
        .map(
            e ->
                GamesItemListResponse.builder()
                    .id(e.getId().toString())
                    .name(e.getName())
                    .type(e.getType().getDescription())
                    .build())
        .toList();
  }
}
