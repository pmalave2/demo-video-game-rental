package com.videogamerental.domain.service;

import java.util.UUID;

public interface OrderDomainService {
  Object createRentalOrder(Object obj);
  
  Object calculateGameReturn(UUID id);
}
