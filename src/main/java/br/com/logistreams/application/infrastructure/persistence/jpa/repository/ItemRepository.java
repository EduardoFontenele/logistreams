package br.com.logistreams.application.infrastructure.persistence.jpa.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
