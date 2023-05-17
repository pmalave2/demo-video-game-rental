package com.videogamerental.domain.repository;

import com.videogamerental.domain.Game;
import java.util.UUID;

public interface GameReadById {
  Game findById(UUID id);
}
