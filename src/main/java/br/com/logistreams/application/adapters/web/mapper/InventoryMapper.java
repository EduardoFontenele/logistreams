package br.com.logistreams.application.adapters.web.mapper;

import br.com.logistreams.application.adapters.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.adapters.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.adapters.web.dto.output.inventory.ListInventoryDTO;
import br.com.logistreams.application.adapters.web.controller.inventory.FindInventoryByIdEndpoint;
import br.com.logistreams.application.adapters.web.controller.inventory.ListInventoryEndpoint;
import br.com.logistreams.application.core.domain.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.HashMap;
import java.util.Map;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "name", target = "name", qualifiedByName = "trimString")
    public abstract Inventory toDomain(CreateInventoryDTO createInventoryDTO);

    @Named("trimString")
    String trimString(String name) {
        return name.trim();
    }

    public InventoryEntity toEntity(Inventory inventory) {
        InventoryEntity inventoryEntity = new InventoryEntity();

        if(inventory.getId() != null) inventoryEntity.setId(inventory.getId());
        inventoryEntity.setId(null);
        inventoryEntity.setName(inventory.getName());

        return inventoryEntity;
    }

    public Inventory toDomain(InventoryEntity entity) {
        Inventory inventory = new Inventory();
        inventory.setId(entity.getId());
        inventory.setName(entity.getName());

        return inventory;
    }

    public ListInventoryDTO toInventoryOutputDTO(Inventory inventory) {
        ListInventoryDTO result = new ListInventoryDTO();
        Map<String, Object> links = new HashMap<>();

        links.put("self", WebMvcLinkBuilder.linkTo(ListInventoryEndpoint.class)
                .slash(inventory.getId())
                .toUriComponentsBuilder()
                .toUriString());

        links.put("inventories", WebMvcLinkBuilder.linkTo(FindInventoryByIdEndpoint.class)
                .toUriComponentsBuilder()
                .toUriString());

        result.setId(inventory.getId());
        result.setName(inventory.getName());
        result.set_links(links);

        return result;
    }
}
