package br.com.logistreams.application.infrastructure.web.mapper;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.domain.entity.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class InventoryMapperTest {
    private final InventoryMapper mapper = Mappers.getMapper(InventoryMapper.class);

    @DisplayName("When mapping from InventoryInputDTO to Inventory with white spaces, should trim and map correctly")
    @Test
    void test1() {
        InventoryInputDTO inputDTO = new InventoryInputDTO("   Camisetas   ");
        Inventory inventory = mapper.toDomain(inputDTO);
        assertEquals("Camisetas", inventory.getName());
    }

    @Test
    @DisplayName("When mapping from InventoryInputDTO to Inventory without white spaces, should map correctly")
    void test2() {
        InventoryInputDTO inputDTO = new InventoryInputDTO("Camisetas");
        Inventory inventory = mapper.toDomain(inputDTO);
        assertEquals("Camisetas", inventory.getName(), "Should map correctly without trimming");
    }
}