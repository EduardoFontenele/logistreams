package br.com.logistreams.domain.repository;

import br.com.logistreams.domain.entity.Inventory;

import java.util.List;

public interface InventoryRepository {
    boolean existsByName(String name);
    void saveNew(Inventory inventory);
    List<Inventory> listInventories(int pageNumber, int pageSize);
    long count();
}
