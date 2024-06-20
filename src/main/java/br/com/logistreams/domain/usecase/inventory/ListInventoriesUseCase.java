package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;

import java.util.List;

public class ListInventoriesUseCase implements ListInventoriesInputPort {
    private final InventoryRepository inventoryRepository;

    public ListInventoriesUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public List<Inventory> execute(int pageNumber, int pageSize) {
        return inventoryRepository.listInventories(pageNumber, pageSize);
    }
}
