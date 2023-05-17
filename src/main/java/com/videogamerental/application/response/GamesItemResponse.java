package com.videogamerental.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GamesItemResponse {
  private String id;
  private String name;
  private String type;
  private Integer daysRented;
  private Double price;
}
