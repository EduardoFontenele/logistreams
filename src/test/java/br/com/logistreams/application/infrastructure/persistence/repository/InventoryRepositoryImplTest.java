package br.com.logistreams.application.infrastructure.persistence.repository;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.JpaInventoryRepository;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryRepositoryImplTest {
    @Mock
    private JpaInventoryRepository jpaInventoryRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryRepositoryImpl inventoryRepository;

    @Test
    @DisplayName("Given an inventory name, when existsByName, should return true if it exists")
    public void testExistsByName_ExistingInventory() throws Exception {
        String name = "Test Inventory";
        when(jpaInventoryRepository.existsByName(name)).thenReturn(true);

        boolean exists = inventoryRepository.existsByName(name);

        assertTrue(exists);
        Mockito.verify(jpaInventoryRepository).existsByName(name);
    }

    @Test
    @DisplayName("Given an inventory name that doesn't exist, when existsByName, should return false")
    public void testExistsByName_NonExistingInventory() throws Exception {
        String name = "Non-existent Inventory";
        when(jpaInventoryRepository.existsByName(name)).thenReturn(false);

        boolean exists = inventoryRepository.existsByName(name);

        assertFalse(exists);
        Mockito.verify(jpaInventoryRepository).existsByName(name);
    }

    @Test
    @DisplayName("Given a valid inventory, when saveNew, should save the entity and return successfully")
    public void testSaveNew_ValidInventory() throws Exception {
        Inventory inventory = new Inventory(null, "New Inventory");
        InventoryEntity expectedEntity = new InventoryEntity(null, "New Inventory", null);
        when(inventoryMapper.toEntity(inventory)).thenReturn(expectedEntity);

        inventoryRepository.saveNew(inventory);

        Mockito.verify(inventoryMapper).toEntity(inventory);
        Mockito.verify(jpaInventoryRepository).save(expectedEntity);
    }

    @Test
    @DisplayName("Given a inventory ID, when inventory entity is present, should return inventory")
    void testFindById_ValidInventory() {
        Inventory inventory = new Inventory(1L, "Camisas");
        InventoryEntity inventoryEntity = new InventoryEntity(1L, "Camisas", new HashSet<>());

        given(jpaInventoryRepository.findById(1L)).willReturn(Optional.of(inventoryEntity));
        given(inventoryMapper.toDomain(inventoryEntity)).willReturn(inventory);

        Inventory foundInventory = inventoryRepository.findById(1L);

        assertNotNull(foundInventory);
        assertEquals(inventoryEntity.getId(), foundInventory.getId());
        assertEquals(inventoryEntity.getName(), foundInventory.getName());
        verify(inventoryMapper, times(1)).toDomain(inventoryEntity);
    }

    @Test
    @DisplayName("Given a inventory ID, when inventory entity is not present, should return null")
    void testFindById_NotFoundInventory() {
        given(jpaInventoryRepository.findById(1L)).willReturn(Optional.empty());
        Inventory foundInventory = inventoryRepository.findById(1L);
        assertNull(foundInventory);
        verifyNoInteractions(inventoryMapper);
    }
}