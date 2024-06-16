package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.domain.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;

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
