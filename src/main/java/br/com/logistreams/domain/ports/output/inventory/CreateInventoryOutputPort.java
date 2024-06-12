package br.com.logistreams.domain.ports.output.inventory;

import br.com.logistreams.domain.entity.Inventory;

public interface CreateInventoryOutputPort {
    void execute(Inventory inventory);
}
