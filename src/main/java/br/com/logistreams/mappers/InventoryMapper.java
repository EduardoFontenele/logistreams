package br.com.logistreams.mappers;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.entities.Inventory;
import org.mapstruct.Mapper;

@Mapper
public abstract class InventoryMapper {

    //public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);
    public abstract Inventory toInventory(InventoryInputDTO inventoryInputDTO);
}
