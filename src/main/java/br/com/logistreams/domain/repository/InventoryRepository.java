package br.com.logistreams.domain.repository;

import br.com.logistreams.domain.entity.Inventory;

public interface InventoryRepository {
    boolean existsByName(String name);
    void saveNew(Inventory inventory);
}
