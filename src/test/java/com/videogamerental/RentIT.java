package com.videogamerental;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = DemoApplication.class)
@AutoConfigureMockMvc
@DirtiesContext
class RentIT {
  @Autowired private MockMvc mvc;

  @Test
  @Transactional
  void givenRentOrderId_whenGetRentOrder_thenReturnGamesRentResponse() throws Exception {
    mvc.perform(
            get("/rents/f8931833-af4d-4637-b907-a995105dc6ed").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.idOrder", is("f8931833-af4d-4637-b907-a995105dc6ed")))
        .andExpect(jsonPath("$.games", hasSize(2)))
        .andExpect(jsonPath("$.total", is(52.0)));
  }

  @Test
  @Transactional
  void givenWrongRentOrderId_whenGetRentOrder_thenBadRequestResponse() throws Exception {
    mvc.perform(
            get("/rents/f8931833-af4d-4637-b907-a995105dc6ea").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
