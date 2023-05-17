package com.videogamerental.application.rest;

import com.videogamerental.application.response.GamesItemListResponse;
import com.videogamerental.domain.service.GameDomainService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/games")
public class GameController {
  private GameDomainService gameDomainService;

  @GetMapping
  public List<GamesItemListResponse> findAllGames() {
    return gameDomainService.findAllGames();
  }
}
