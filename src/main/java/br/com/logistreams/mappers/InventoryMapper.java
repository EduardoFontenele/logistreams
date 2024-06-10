package br.com.logistreams.mappers;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "name", target = "name")
    public abstract Inventory toInventory(InventoryInputDTO inventoryInputDTO);

    public  List<InventoryOutputDTO> toInventoryOutputDTOList(List<Inventory> inventoryList) {
        List<InventoryOutputDTO> result = new ArrayList<>();
        for(Inventory inventory : inventoryList) {
            result.add(new InventoryOutputDTO(inventory.getId(), inventory.getName(), new HashSet<>()));
        }
        return result;
    }

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "sections", target = "sections", ignore = true)
    public abstract InventoryOutputDTO toInventoryOutputDTO(Inventory inventory);
}
