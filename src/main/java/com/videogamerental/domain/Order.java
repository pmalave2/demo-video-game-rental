package com.videogamerental.domain;

import java.time.LocalDate;
import java.util.List;
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
public class Order {
  private UUID id;
  private LocalDate date;
  private UUID idCustomer;
  private List<Game> games;
}
