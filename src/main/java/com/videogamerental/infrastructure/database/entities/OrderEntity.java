package com.videogamerental.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;
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
public class OrderEntity {
  @Id private UUID id;
  private LocalDate date;
  private UUID idCustomer;

  @OneToMany(mappedBy = "order")
  private Set<OrderItemEntity> items;
}
