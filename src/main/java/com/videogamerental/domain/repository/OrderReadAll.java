package com.videogamerental.domain.repository;

import com.videogamerental.domain.Order;
import java.util.List;

public interface OrderReadAll {
  List<Order> findAll();
}
