package br.com.logistreams.application.infrastructure.persistence.jpa.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    boolean existsByName(String name);
}
