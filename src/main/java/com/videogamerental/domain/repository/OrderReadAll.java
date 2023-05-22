package com.videogamerental.domain.repository;

import java.util.List;

import com.videogamerental.domain.Order;

public interface OrderReadAll {
  List<Order> findAll();
}
