package br.com.logistreams.application.infrastructure.web.adapter.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.output.inventory.CreateInventoryOutputPort;
import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateInventoryWebAdapter implements CreateInventoryOutputPort {
    private final InventoryRepository inventoryRepository;

    @Override
    public void execute(Inventory inventory) {
        if(inventoryRepository.existsByName(inventory.getName())) {
            throw new BusinessLogicException(HttpStatus.CONFLICT, String.format("Resource with name '%s' already exists", inventory.getName()));
        }

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setName(inventory.getName());
        inventoryRepository.save(inventoryEntity);

    }
}
