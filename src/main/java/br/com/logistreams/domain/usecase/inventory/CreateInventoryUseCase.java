package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.utils.LoggerService;

public class CreateInventoryUseCase implements CreateInventoryInputPort {
    private final InventoryRepository inventoryRepository;
    private final LoggerService log = new LoggerService(org.slf4j.LoggerFactory.getLogger(CreateInventoryUseCase.class));

    public CreateInventoryUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void execute(String inventoryName) {
        if(inventoryRepository.existsByName(inventoryName)) {
            log.conflictError();
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
        }

        Inventory inventory = new Inventory(inventoryName);
        inventoryRepository.saveNew(inventory);
    }
}
