package com.videogamerental.application.response;

import java.time.LocalDate;
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
public class GamesReturnResponse {
  private LocalDate rentDate;
  private List<GamesItemReturnResponse> games;
  private Double total;
}
