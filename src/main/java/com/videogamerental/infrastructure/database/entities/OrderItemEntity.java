package com.videogamerental.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class OrderItemEntity {
  @Id private UUID id;
  private Integer daysRented;

  @ManyToOne private OrderEntity order;
  @OneToOne private GameEntity game;
}
