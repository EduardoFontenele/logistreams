package br.com.logistreams.infrastructure.persistence.jpa;

import br.com.logistreams.domain.model.Inventory;
import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.infrastructure.persistence.entity.InventoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaInventoryRepositoryImpl implements InventoryRepository {
    private final JpaInventoryRepository jpaInventoryRepository;

    @Autowired
    public JpaInventoryRepositoryImpl(JpaInventoryRepository jpaInventoryRepository) {
        this.jpaInventoryRepository = jpaInventoryRepository;
    }

    @Override
    public void createNewInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .name("TEST 1")
                .build();

        jpaInventoryRepository.save(inventoryEntity);
    }
}
