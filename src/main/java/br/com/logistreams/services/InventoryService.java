package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;

import java.util.List;

public interface InventoryService {
    void createNewInventory(InventoryInputDTO inventoryInputDTO);
    List<InventoryOutputDTO> listAll();
    void deleteById(int id);
    InventoryOutputDTO findById(int id);
    InventoryOutputDTO updateInventory(int id, InventoryInputDTO inventoryInputDTO);
}
