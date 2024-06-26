package br.com.logistreams.application.adapters.persistence.repository;

import br.com.logistreams.application.adapters.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.adapters.persistence.jpa.repository.JpaInventoryRepository;
import br.com.logistreams.application.adapters.web.mapper.InventoryMapper;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Inventory findById(Long id) {
        Optional<InventoryEntity> inventoryEntity = jpaInventoryRepository.findById(1L);
        return inventoryEntity.map(inventoryMapper::toDomain).orElseGet(() -> null);
    }

    @Override
    public boolean existsById(long id) {
        return jpaInventoryRepository.existsById(id);
    }

    @Override
    public void deleteById(long id) {
        jpaInventoryRepository.deleteById(id);
    }
}
