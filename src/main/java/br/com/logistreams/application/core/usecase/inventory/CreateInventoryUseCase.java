package br.com.logistreams.application.core.usecase.inventory;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.utils.LoggerService;
import org.slf4j.LoggerFactory;

public class CreateInventoryUseCase implements CreateInventoryInputPort {
    private final InventoryRepository inventoryRepository;
    private final LoggerService log = new LoggerService(LoggerFactory.getLogger(CreateInventoryUseCase.class));

    public CreateInventoryUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void execute(String inventoryName) {
        if(inventoryRepository.existsByName(inventoryName)) {
            log.error(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
        }

        Inventory inventory = new Inventory(inventoryName);
        inventoryRepository.saveNew(inventory);
    }
}
