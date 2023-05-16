package com.videogamerental.domain.repository;

import com.videogamerental.domain.Order;
import java.util.UUID;

public interface OrderRead {
  Order findById(UUID id);
}
