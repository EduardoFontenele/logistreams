package br.com.logistreams.application.core.usecase.inventory;

import br.com.logistreams.application.core.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.application.core.repository.InventoryRepository;

public class CountInventoriesUseCase implements CountInventoriesInputPort {
    private final InventoryRepository inventoryRepository;

    public CountInventoriesUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public long execute() {
        return inventoryRepository.count();
    }
}
