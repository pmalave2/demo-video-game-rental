package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.domain.GameType;
import com.videogamerental.domain.Order;
import com.videogamerental.domain.repository.OrderRead;
import com.videogamerental.domain.repository.OrderReadAll;
import com.videogamerental.domain.repository.OrderSave;
import com.videogamerental.infrastructure.database.entities.LoyaltyEntity;
import com.videogamerental.infrastructure.database.entities.OrderEntity;
import com.videogamerental.infrastructure.database.entities.OrderItemEntity;
import com.videogamerental.infrastructure.exceptions.GameNotFoundInfrastructureException;
import com.videogamerental.infrastructure.exceptions.OrderNotFoundInfrastructureException;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderAdapter implements OrderRead, OrderSave, OrderReadAll {
  private final H2OrderRepository h2OrderRepository;
  private final H2OrderItemRepository h2OrderItemRepository;
  private final H2GameRepository h2GameRepository;
  private final H2LoyaltyRepository h2LoyaltyRepository;

  @Override
  public Order findById(UUID id) {
    var entityOptional = h2OrderRepository.findById(id);

    if (!entityOptional.isPresent()) throw new OrderNotFoundInfrastructureException();

    return mapOrderEntityToOrder(entityOptional.get());
  }

  @Override
  public void save(Order order) {
    for (var game : order.getGames()) {
      if (!h2GameRepository.existsById(game.getId()))
        throw new GameNotFoundInfrastructureException(
            String.format("Game %s not found", game.getId()));
    }

    saveOrder(order);

    updateLoyalty(order);
  }

  private void saveOrder(Order order) {
    var orderEntity =
        OrderEntity.builder()
            .id(order.getId())
            .idCustomer(order.getIdCustomer())
            .date(order.getDate())
            .build();

    var orderItemEntity =
        order.getGames().stream()
            .map(
                elem ->
                    OrderItemEntity.builder()
                        .id(UUID.randomUUID())
                        .daysRented(elem.getDaysRented())
                        .game(h2GameRepository.getReferenceById(elem.getId()))
                        .order(orderEntity)
                        .build())
            .toList();

    h2OrderRepository.save(orderEntity);
    h2OrderItemRepository.saveAll(orderItemEntity);
  }

  private void updateLoyalty(Order order) {
    var points = 0L;
    for (var game : order.getGames()) {
      if (GameType.N.equals(game.getType())) points += 2;
      else points += 1;
    }

    var loyaltyEntityOptional = h2LoyaltyRepository.findByIdCustomer(order.getIdCustomer());
    if (loyaltyEntityOptional.isPresent()) {
      var loyaltyEntity = loyaltyEntityOptional.get();
      loyaltyEntity.setPoints(loyaltyEntity.getPoints() + points);
      h2LoyaltyRepository.save(loyaltyEntity);
    } else {
      var loyaltyEntity =
          LoyaltyEntity.builder()
              .id(UUID.randomUUID())
              .idCustomer(order.getIdCustomer())
              .points(points)
              .build();
      h2LoyaltyRepository.save(loyaltyEntity);
    }
  }

  @Override
  public List<Order> findAll() {
    return h2OrderRepository.findAll().stream().map(this::mapOrderEntityToOrder).toList();
  }

  private Order mapOrderEntityToOrder(OrderEntity entity) {
    return Order.builder()
        .id(entity.getId())
        .idCustomer(entity.getIdCustomer())
        .date(entity.getDate())
        .games(
            entity.getItems().stream()
                .map(
                    elem ->
                        GameType.valueOf(elem.getGame().getProperties().getType().toString())
                            .factory()
                            .createDomainGame(elem))
                .toList())
        .build();
  }
}
