package com.videogamerental.domain.repository;

import com.videogamerental.domain.Game;
import java.util.List;

public interface GameRead {
  List<Game> findAll();
}
