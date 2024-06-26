package br.com.logistreams.application.adapters.persistence.jpa.repository;

import br.com.logistreams.application.adapters.persistence.jpa.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Long> {
    boolean existsByName(String name);
}
