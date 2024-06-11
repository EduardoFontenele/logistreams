package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.PagedResponse;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;

public interface InventoryService {
    void createNewInventory(InventoryInputDTO inventoryInputDTO);
    PagedResponse<InventoryOutputDTO> listAll(Integer pageNumber, Integer pageSize);
    void deleteById(int id);
    InventoryOutputDTO findById(int id);
    InventoryOutputDTO updateInventory(int id, InventoryInputDTO inventoryInputDTO);
}
