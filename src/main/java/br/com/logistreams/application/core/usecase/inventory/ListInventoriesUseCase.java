package br.com.logistreams.application.core.usecase.inventory;

import br.com.logistreams.application.core.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;

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
