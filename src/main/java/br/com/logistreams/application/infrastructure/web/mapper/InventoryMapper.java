package br.com.logistreams.application.infrastructure.web.mapper;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.domain.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(source = "name", target = "name", qualifiedByName = "trimString")
    public abstract Inventory toDomain(InventoryInputDTO inventoryInputDTO);

    @Named("trimString")
    String trimString(String name) {
        return name.trim();
    }
}
