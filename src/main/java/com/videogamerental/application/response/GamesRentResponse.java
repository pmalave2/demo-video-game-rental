package com.videogamerental.application.response;

import java.util.List;
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
public class GamesRentResponse {
  private String idOrder;
  private List<GamesItemResponse> games;
  private Double total;
}
