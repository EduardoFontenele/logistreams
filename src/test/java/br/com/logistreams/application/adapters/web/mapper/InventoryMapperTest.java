package br.com.logistreams.application.adapters.web.mapper;

import br.com.logistreams.application.adapters.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.adapters.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.core.domain.Inventory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryMapperTest {
    @Test
    @DisplayName("Should map InventoryInputDTO to Inventory, trimming name")
    void testToDomain_TrimsName() {
        // Given an InventoryInputDTO with leading/trailing spaces
        String nameWithSpaces = "  Inventory Name  ";
        CreateInventoryDTO createInventoryDTO = new CreateInventoryDTO(nameWithSpaces);

        // When mapping to Inventory
        Inventory inventory = InventoryMapper.INSTANCE.toDomain(createInventoryDTO);

        // Then the name in Inventory should be trimmed
        assertEquals("Inventory Name", inventory.getName());
    }

    @Test
    @DisplayName("Should map Inventory to InventoryEntity, ignoring null ID")
    void testToEntity_IgnoresNullId() {
        // Given an Inventory with null ID
        Inventory inventory = new Inventory(null, "Inventory Name");

        // When mapping to InventoryEntity
        InventoryEntity inventoryEntity = InventoryMapper.INSTANCE.toEntity(inventory);

        // Then the ID in InventoryEntity should be null
        assertNull(inventoryEntity.getId());
    }

    @Test
    @DisplayName("Should map Inventory to InventoryEntity, handling empty sections")
    void testToEntity_HandlesEmptySections() {
        // Given an Inventory with null sections
        Inventory inventory = new Inventory(null, "Inventory Name");

        // When mapping to InventoryEntity
        InventoryEntity inventoryEntity = InventoryMapper.INSTANCE.toEntity(inventory);

        // Then the sections in InventoryEntity should be an empty set
        assertNotNull(inventoryEntity.getSections());
    }
}