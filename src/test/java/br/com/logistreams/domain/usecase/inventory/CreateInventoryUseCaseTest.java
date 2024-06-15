package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.repository.InventoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CreateInventoryUseCaseTest {
    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    CreateInventoryUseCase createInventoryUseCase;

    @Test
    @DisplayName("Should save inventory successfully when name is unique")
    void testExecute_UniqueName_SavesInventory() throws Exception {
        final String inventoryName = "Unique Inventory Name";

        when(inventoryRepository.existsByName(inventoryName)).thenReturn(false);

        createInventoryUseCase.execute(inventoryName);

        verify(inventoryRepository, times(1)).existsByName(inventoryName);
        verify(inventoryRepository, times(1)).saveNew(any(Inventory.class));
    }

    @Test
    @DisplayName("Should throw exception when saving inventory with duplicate name")
    void testExecute_DuplicateName_ThrowsException() throws Exception {
        // Given a duplicate inventory name
        String inventoryName = "Duplicate Inventory Name";

        // Mock repository behavior
        when(inventoryRepository.existsByName(inventoryName)).thenReturn(true);

        assertThrows(BusinessLogicException.class, () -> createInventoryUseCase.execute(inventoryName));

        // Verify only existsByName is called on the repository
        verify(inventoryRepository, times(1)).existsByName(inventoryName);
        verify(inventoryRepository, times(0)).saveNew(any(Inventory.class));
    }
}