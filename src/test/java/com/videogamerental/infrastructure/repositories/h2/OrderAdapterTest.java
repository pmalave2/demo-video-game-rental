package com.videogamerental.infrastructure.repositories.h2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.videogamerental.infrastructure.database.entities.LoyaltyEntity;
import com.videogamerental.infrastructure.database.entities.OrderEntity;
import com.videogamerental.infrastructure.exceptions.GameNotFoundInfrastructureException;
import com.videogamerental.infrastructure.exceptions.OrderNotFoundInfrastructureException;
import com.videogamerental.utils.ObjectsGenerator;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class OrderAdapterTest {
  @Mock H2OrderRepository h2OrderRepository;
  @Mock H2OrderItemRepository h2OrderItemRepository;
  @Mock H2GameRepository h2GameRepository;
  @Mock H2LoyaltyRepository h2LoyaltyRepository;

  @InjectMocks private OrderAdapter orderAdapter;

  @Test
  void givenUUID_whenGetOrder_thenReturnOrder() {
    var order = ObjectsGenerator.orderEntity();
    var orderUuid = UUID.fromString("a42c995d-57ac-4eac-a6de-be1bae5ddf17");
    when(h2OrderRepository.findById(orderUuid)).thenReturn(Optional.of(order));

    var response = orderAdapter.findById(orderUuid);

    assertEquals(orderUuid, response.getId());
    assertEquals(1, response.getGames().size());
  }

  @Test
  void givenUUID_whenGetOrder_thenReturnOrderNotFoundInfrastructureException() {
    var uuid = UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539a");
    when(h2OrderRepository.findById(uuid)).thenReturn(Optional.empty());

    assertThrows(OrderNotFoundInfrastructureException.class, () -> orderAdapter.findById(uuid));
  }

  @Test
  void givenOrder_whenSaveOrder_thenReturnGameNotFoundInfrastructureException() {
    var order = ObjectsGenerator.orderDomain();
    var gameUuid = UUID.fromString("72a759bb-17c7-4151-b342-84d5c535539a");
    when(h2GameRepository.existsById(gameUuid)).thenReturn(false);

    assertThrows(GameNotFoundInfrastructureException.class, () -> orderAdapter.save(order));
  }

  @Test
  void givenOrder_whenSaveOrder_thenOrderSaved() {
    var order = ObjectsGenerator.orderDomain();
    when(h2GameRepository.existsById(any(UUID.class))).thenReturn(true);

    orderAdapter.save(order);

    verify(h2OrderRepository).save(any(OrderEntity.class));
    verify(h2OrderItemRepository).saveAll(anyIterable());
    verify(h2LoyaltyRepository).save(any(LoyaltyEntity.class));
  }
}
