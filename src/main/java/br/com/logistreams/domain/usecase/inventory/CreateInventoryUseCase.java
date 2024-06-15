package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;
import org.slf4j.Logger;

import java.util.HashSet;

public class CreateInventoryUseCase implements CreateInventoryInputPort {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CreateInventoryUseCase.class);
    private final InventoryRepository inventoryRepository;

    public CreateInventoryUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void execute(String inventoryName) {
        if(inventoryRepository.existsByName(inventoryName)) {
            log.error("[CodeError: {}] - Resource with name '{}' already exists", ErrorsEnum.RESOURCE_ALREADY_EXISTS.getCodeError(), inventoryName);
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
        }

        Inventory inventory = new Inventory(null, inventoryName, new HashSet<>());
        inventoryRepository.saveNew(inventory);
    }
}
