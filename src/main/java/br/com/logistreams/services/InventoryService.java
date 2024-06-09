package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;

public interface InventoryService {
    void createNewInventory(InventoryInputDTO inventoryInputDTO);
}
