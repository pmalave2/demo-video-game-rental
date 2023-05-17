package com.videogamerental.infrastructure.repositories.h2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.videogamerental.infrastructure.exceptions.GameNotFoundInfrastructureException;
import com.videogamerental.utils.ObjectsGenerator;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GamesAdapterTest {
  @Mock private H2GameRepository repository;

  @InjectMocks private GamesAdapter gamesAdapter;

  @Test
  void givenUUID_whenGetGameById_thenReturnGame() {
    var game = ObjectsGenerator.gameEntities().get(0);
    var uuid = UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539d");
    when(repository.findById(uuid)).thenReturn(Optional.of(game));

    var response = gamesAdapter.findById(uuid);

    assertEquals(uuid, response.getId());
  }

  @Test
  void givenUUID_whenGetGameById_thenReturnGameNotFoundInfrastructureException() {
    var uuid = UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539a");
    when(repository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(GameNotFoundInfrastructureException.class, () -> gamesAdapter.findById(uuid));
  }

  @Test
  void givenRequest_whenGetAllGame_thenGameListWithThreeElements() {
    var games = ObjectsGenerator.gameEntities();
    when(repository.findAll()).thenReturn(games);

    var response = gamesAdapter.findAll();

    assertEquals(3, response.size());
  }
}
