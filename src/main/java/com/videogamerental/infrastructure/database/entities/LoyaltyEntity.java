package com.videogamerental.infrastructure.database.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class LoyaltyEntity {
  @Id private UUID id;
  private UUID idCustomer;
  private Long points;
}
