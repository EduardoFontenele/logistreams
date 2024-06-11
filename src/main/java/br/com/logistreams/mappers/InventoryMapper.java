package br.com.logistreams.mappers;

import br.com.logistreams.controllers.InventoryController;
import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "name", target = "name")
    public abstract Inventory toInventory(InventoryInputDTO inventoryInputDTO);

    public List<InventoryOutputDTO> toInventoryOutputDTOList(List<Inventory> inventoryList) {
        List<InventoryOutputDTO> result = new ArrayList<>();
        for(Inventory inventory : inventoryList) {
            result.add(toInventoryOutputDTO(inventory));
        }
        return result;
    }
    
    public InventoryOutputDTO toInventoryOutputDTO(Inventory inventory) {
        InventoryOutputDTO result = new InventoryOutputDTO();
        Map<String, Object> links = new HashMap<>();

        links.put("self", WebMvcLinkBuilder.linkTo(InventoryController.class)
                .slash(inventory.getId())
                .toUriComponentsBuilder()
                .toUriString());

        links.put("inventories", WebMvcLinkBuilder.linkTo(InventoryController.class)
                .toUriComponentsBuilder()
                .toUriString());

        result.setId(inventory.getId());
        result.setName(inventory.getName());
        result.set_links(links);

        return result;
    };

}
