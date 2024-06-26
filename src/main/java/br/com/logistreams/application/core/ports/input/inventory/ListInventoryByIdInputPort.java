package br.com.logistreams.application.core.ports.input.inventory;

import br.com.logistreams.application.core.domain.Inventory;

public interface ListInventoryByIdInputPort {
    Inventory execute(Long id);
}
