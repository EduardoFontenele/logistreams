package br.com.logistreams.application.infrastructure.web.adapter.inventory;

import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.output.inventory.CreateInventoryOutputPort;
import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInventoryWebAdapter implements CreateInventoryOutputPort {
    private final InventoryRepository inventoryRepository;

    @Override
    public void execute(Inventory inventory) {
        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setName(inventory.getName());
        inventoryRepository.save(inventoryEntity);
    }
}
