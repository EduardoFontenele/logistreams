package br.com.logistreams.application.infrastructure.web.mapper;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.entity.SectionEntity;
import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.domain.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.HashSet;
import java.util.Set;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "name", target = "name", qualifiedByName = "trimString")
    public abstract Inventory toDomain(InventoryInputDTO inventoryInputDTO);

    @Named("trimString")
    String trimString(String name) {
        return name.trim();
    }

    public InventoryEntity toEntity(Inventory inventory) {
        InventoryEntity inventoryEntity = new InventoryEntity();

        if(inventory.getId() != null) inventoryEntity.setId(inventory.getId());
        inventoryEntity.setId(null);

        inventoryEntity.setName(inventory.getName());

        if(inventory.getSections() != null) inventoryEntity.setSections(new HashSet<>());
        inventoryEntity.setSections(null);

        return inventoryEntity;
    }
}
