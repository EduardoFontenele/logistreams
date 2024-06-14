package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.domain.ports.output.inventory.CreateInventoryOutputPort;

public class CreateInventoryUseCase implements CreateInventoryInputPort {
    private final CreateInventoryOutputPort createInventoryOutputPort;

    public CreateInventoryUseCase(CreateInventoryOutputPort createInventoryOutputPort) {
        this.createInventoryOutputPort = createInventoryOutputPort;
    }

    @Override
    public void execute(Inventory inventory) {
        createInventoryOutputPort.execute(inventory);
    }
}
