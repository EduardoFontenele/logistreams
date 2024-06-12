package br.com.logistreams.domain.repository;

import br.com.logistreams.domain.model.Inventory;

public interface InventoryRepository {
    void createNewInventory(Inventory inventory);
}
