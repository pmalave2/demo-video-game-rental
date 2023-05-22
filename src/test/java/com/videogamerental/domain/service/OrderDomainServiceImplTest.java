package com.videogamerental.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.videogamerental.domain.Order;
import com.videogamerental.domain.repository.GameReadById;
import com.videogamerental.domain.repository.OrderRead;
import com.videogamerental.domain.repository.OrderReadAll;
import com.videogamerental.domain.repository.OrderSave;
import com.videogamerental.utils.ObjectsGenerator;

@ExtendWith(SpringExtension.class)
class OrderDomainServiceImplTest {
  @Mock GameReadById gameReadById;
  @Mock OrderSave orderSave;
  @Mock OrderRead orderRead;
  @Mock OrderReadAll orderReadAll;

  @InjectMocks OrderDomainServiceImpl orderDomainService;

  @Test
  void givenUUID_whenGetRentalOrder_thenReturnGamesRentResponseWithRentCost32() {
    // Given
    var order = ObjectsGenerator.orderDomain();
    var orderUuid = order.getId();
    when(orderRead.findById(orderUuid)).thenReturn(order);

    // When
    var response = orderDomainService.getRentalOrder(orderUuid);

    // Then
    verify(orderRead).findById(any(UUID.class));
    assertEquals(32, response.getTotal());
  }

  @Test
  void
      givenUUIDAndGamesReturnRequest_whenCalculateGameReturn_thenReturnGamesReturnResponseWithOverdueCost0() {
    // Given
    var order = ObjectsGenerator.orderDomain();
    var orderUuid = order.getId();
    var request = ObjectsGenerator.gamesReturnRequest();
    when(orderRead.findById(orderUuid)).thenReturn(order);

    // When
    var response = orderDomainService.calculateGameReturn(orderUuid, request);

    // Then
    verify(orderRead).findById(any(UUID.class));
    assertEquals(1, response.getGames().size());
    assertEquals(0, response.getTotal());
  }

  @Test
  void
      givenUUIDAndGamesReturnRequest_whenCalculateGameReturnWithOverdueOf6Days_thenReturnGamesReturnResponseWithOverdueCost7() {
    // Given
    var order = ObjectsGenerator.orderDomain();
    order.setDate(LocalDate.now().minusDays(6));
    var orderUuid = order.getId();
    var request = ObjectsGenerator.gamesReturnRequest();
    request.setGamesIds(
        List.of("72a759bb-17c7-4151-b342-84d5c535539d", "f1f4d6fd-367d-4a3e-bd0c-17a060516c57"));
    when(orderRead.findById(orderUuid)).thenReturn(order);

    // When
    var response = orderDomainService.calculateGameReturn(orderUuid, request);

    // Then
    verify(orderRead).findById(any(UUID.class));
    assertEquals(2, response.getGames().size());
    assertEquals(7, response.getTotal());
  }

  @Test
  void givenGamesRentRequest_whenSaveRentalOrder_thenGamesRentResponseWithRentCost29() {
    // Given
    var request = ObjectsGenerator.gamesRentRequest();
    var game = ObjectsGenerator.gamesDomain().get(0);
    when(gameReadById.findById(game.getId())).thenReturn(game);
    var game2 = ObjectsGenerator.gamesDomain().get(1);
    when(gameReadById.findById(game2.getId())).thenReturn(game2);

    // When
    var response = orderDomainService.createRentalOrder(request);

    // Then
    verify(gameReadById, times(2)).findById(any(UUID.class));
    verify(orderSave).save(any(Order.class));
    assertEquals(29, response.getTotal());
  }

  @Test
  void givenRequest_whenGetAllRentalOrders_thenReturnGamesRentResponseListWithOneElement() {
    // Given
    var orders = List.of(ObjectsGenerator.orderDomain());
    when(orderReadAll.findAll()).thenReturn(orders);

    // When
    var response = orderDomainService.getRentalOrders();

    // Then
    verify(orderReadAll).findAll();
    assertEquals(1, response.size());
  }

}
