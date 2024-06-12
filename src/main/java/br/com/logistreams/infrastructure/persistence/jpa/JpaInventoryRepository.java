package br.com.logistreams.infrastructure.persistence.jpa;

import br.com.logistreams.infrastructure.persistence.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface JpaInventoryRepository extends JpaRepository<InventoryEntity, Integer> {
}
