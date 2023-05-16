package com.videogamerental.infrastructure.repositories.h2;

import com.videogamerental.infrastructure.database.entities.LoyaltyEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2LoyaltyRepository extends JpaRepository<LoyaltyEntity, UUID> {
  Optional<LoyaltyEntity> findByIdCustomer(UUID idCustomer);
}
