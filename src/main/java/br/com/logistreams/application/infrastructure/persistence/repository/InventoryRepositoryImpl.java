package br.com.logistreams.application.infrastructure.persistence.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.JpaInventoryRepository;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<Inventory> listInventories(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<InventoryEntity> inventoryEntityPage = jpaInventoryRepository.findAll(pageRequest);

        return inventoryEntityPage.stream()
                .map(inventoryMapper::toDomain)
                .toList();
    }

    @Override
    public long count() {
        return jpaInventoryRepository.count();
    }
}
