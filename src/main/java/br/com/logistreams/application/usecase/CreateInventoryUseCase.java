package br.com.logistreams.application.usecase;

import br.com.logistreams.domain.model.Inventory;

public interface CreateInventoryUseCase {
    void execute(Inventory inventory);
}
