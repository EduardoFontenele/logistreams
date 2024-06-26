package br.com.logistreams.application.core.repository;

import br.com.logistreams.application.core.domain.Inventory;

import java.util.List;

public interface InventoryRepository {
    boolean existsByName(String name);
    void saveNew(Inventory inventory);
    List<Inventory> listInventories(int pageNumber, int pageSize);
    long count();
    Inventory findById(Long id);
    boolean existsById(long l);
    void deleteById(long l);
}
