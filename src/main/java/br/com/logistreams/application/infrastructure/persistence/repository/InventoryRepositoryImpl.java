package br.com.logistreams.application.infrastructure.persistence.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.JpaInventoryRepository;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {
    private final JpaInventoryRepository jpaInventoryRepository;
    private final InventoryMapper inventoryMapper;

    @Override
    public boolean existsByName(String name) {
        return jpaInventoryRepository.existsByName(name);
    }

    @Override
    public void saveNew(Inventory inventory) {
        InventoryEntity inventoryEntity = inventoryMapper.toEntity(inventory);
        jpaInventoryRepository.save(inventoryEntity);
    }
}
