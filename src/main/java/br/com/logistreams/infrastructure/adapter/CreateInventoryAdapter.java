package br.com.logistreams.infrastructure.adapter;

import br.com.logistreams.application.usecase.CreateInventoryUseCase;
import br.com.logistreams.domain.model.Inventory;
import br.com.logistreams.domain.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInventoryAdapter implements CreateInventoryUseCase {
    private final InventoryRepository inventoryRepository;

    @Override
    public void execute(Inventory inventory) {
        inventoryRepository.createNewInventory(inventory);
    }
}
