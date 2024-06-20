package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.ListInventoryByIdInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.utils.LoggerService;
import org.slf4j.LoggerFactory;

public class ListInventoryByIdUseCase implements ListInventoryByIdInputPort {
    private final InventoryRepository inventoryRepository;
    private final LoggerService log = new LoggerService(LoggerFactory.getLogger(ListInventoryByIdUseCase.class));

    public ListInventoryByIdUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory execute(Long id) {
        Inventory inventory = inventoryRepository.findById(id);

        if(inventory == null) {
            log.error(ErrorsEnum.RESOURCE_NOT_FOUND, String.format("Inventory with ID %d not present", id));
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_NOT_FOUND);
        }

        return inventory;
    }
}
