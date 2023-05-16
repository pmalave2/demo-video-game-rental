package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.domain.Game;
import com.videogamerental.domain.Order;
import com.videogamerental.domain.repository.OrderRead;
import com.videogamerental.domain.repository.OrderSave;
import com.videogamerental.infrastructure.database.entities.LoyaltyEntity;
import com.videogamerental.infrastructure.database.entities.OrderEntity;
import com.videogamerental.infrastructure.database.entities.OrderItemEntity;
import com.videogamerental.infrastructure.exceptions.GameNotFoundtInfrastructureException;
import com.videogamerental.infrastructure.exceptions.OrderNotFoundtInfrastructureException;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderAdapter implements OrderRead, OrderSave {

  private final H2OrderRepository h2OrderRepository;
  private final H2OrderItemRepository h2OrderItemRepository;
  private final H2GameRepository h2GameRepository;
  private final H2LoyaltyRepository h2LoyaltyRepository;

  @Override
  public Order findById(UUID id) {
    var entityOptional = h2OrderRepository.findById(id);

    if (!entityOptional.isPresent()) throw new OrderNotFoundtInfrastructureException();

    var entity = entityOptional.get();
    return Order.builder()
        .id(entity.getId())
        .date(entity.getDate())
        .games(
            entity.getItems().stream()
                .map(
                    elem ->
                        Game.builder()
                            .id(elem.getGame().getId())
                            .type(elem.getGame().getProperties().getType())
                            .name(elem.getGame().getName())
                            .rentDays(elem.getGame().getProperties().getRentDays())
                            .rentPrice(elem.getGame().getProperties().getRentPrice())
                            .daysRented(elem.getDaysRented())
                            .build())
                .toList())
        .build();
  }

  @Override
  public void save(Order order) {
    for (Game game : order.getGames()) {
      if (!h2GameRepository.existsById(game.getId()))
        throw new GameNotFoundtInfrastructureException(
            String.format("Game %s not found", game.getId()));
    }

    var orderEntity = OrderEntity.builder().id(order.getId()).date(order.getDate()).build();

    var orderItemEntity =
        order.getGames().stream()
            .map(
                elem ->
                    OrderItemEntity.builder()
                        .id(elem.getId())
                        .daysRented(elem.getDaysRented())
                        .game(h2GameRepository.getReferenceById(elem.getId()))
                        .order(orderEntity)
                        .build())
            .toList();

    h2OrderRepository.save(orderEntity);
    h2OrderItemRepository.saveAll(orderItemEntity);

    var loyaltyEntityOptional = h2LoyaltyRepository.findByIdCustomer(order.getIdCustomer());
    if (loyaltyEntityOptional.isPresent()) {
      var loyaltyEntity = loyaltyEntityOptional.get();
      loyaltyEntity.setPoints(loyaltyEntity.getPoints() + 1);
      h2LoyaltyRepository.save(loyaltyEntity);
    } else {
      var loyaltyEntity =
          LoyaltyEntity.builder()
              .id(UUID.randomUUID())
              .idCustomer(order.getIdCustomer())
              .points(1L)
              .build();
      h2LoyaltyRepository.save(loyaltyEntity);
    }
  }
}
