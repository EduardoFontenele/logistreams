package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.ports.input.inventory.DeleteInventoryByIdInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.utils.LoggerService;
import org.slf4j.LoggerFactory;

public class DeleteInventoryByIdUseCase implements DeleteInventoryByIdInputPort {
    private final InventoryRepository inventoryRepository;
    private final LoggerService log = new LoggerService(LoggerFactory.getLogger(DeleteInventoryByIdUseCase.class));

    public DeleteInventoryByIdUseCase(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void execute(Long id) {
        if(inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
        } else {
            log.error(ErrorsEnum.RESOURCE_NOT_FOUND);
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_NOT_FOUND);
        }
    }
}
