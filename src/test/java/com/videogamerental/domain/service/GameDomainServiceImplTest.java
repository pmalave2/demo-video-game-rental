package com.videogamerental.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.videogamerental.domain.repository.GameRead;
import com.videogamerental.utils.ObjectsGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class GameDomainServiceImplTest {
  @Mock private GameRead gameRead;

  @InjectMocks private GameDomainServiceImpl gameDomainService;

  @Test
  void givenRequest_whenGetGameCatalog_thenReturnGameListWithThreeElement() {
    // Given
    var games = ObjectsGenerator.gamesDomain();
    when(gameRead.findAll()).thenReturn(games);

    // When
    var response = gameDomainService.findAllGames();

    // Then
    verify(gameRead).findAll();
    assertEquals(3, response.size());
  }
}
