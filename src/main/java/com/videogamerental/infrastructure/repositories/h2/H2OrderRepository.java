package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.infrastructure.database.entities.OrderEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2OrderRepository extends JpaRepository<OrderEntity, UUID> {}
