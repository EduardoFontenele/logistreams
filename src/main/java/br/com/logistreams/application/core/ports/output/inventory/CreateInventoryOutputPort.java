package br.com.logistreams.application.core.ports.output.inventory;

import br.com.logistreams.application.core.domain.Inventory;

public interface CreateInventoryOutputPort {
    void execute(Inventory inventory);
}
