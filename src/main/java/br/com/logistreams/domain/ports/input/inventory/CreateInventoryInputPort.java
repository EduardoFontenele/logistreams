package br.com.logistreams.domain.ports.input.inventory;

import br.com.logistreams.domain.entity.Inventory;

public interface CreateInventoryInputPort {
    void execute(Inventory inventory);
}
