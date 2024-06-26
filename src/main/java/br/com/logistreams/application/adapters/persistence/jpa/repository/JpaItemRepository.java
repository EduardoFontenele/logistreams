package br.com.logistreams.application.adapters.persistence.jpa.repository;

import br.com.logistreams.application.adapters.persistence.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<ItemEntity, Long> {
}
