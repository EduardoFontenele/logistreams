package br.com.logistreams.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class InventoryMapper {
    public static final InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);


}
