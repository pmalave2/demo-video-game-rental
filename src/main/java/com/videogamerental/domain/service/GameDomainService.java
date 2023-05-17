package com.videogamerental.domain.service;

import com.videogamerental.application.response.GamesItemListResponse;
import java.util.List;

public interface GameDomainService {
  List<GamesItemListResponse> findAllGames();
}
