package com.videogamerental.infrastructure.repositories.h2;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.videogamerental.infrastructure.database.entities.OrderEntity;

public interface H2OrderRepository extends JpaRepository<OrderEntity, UUID> {}
