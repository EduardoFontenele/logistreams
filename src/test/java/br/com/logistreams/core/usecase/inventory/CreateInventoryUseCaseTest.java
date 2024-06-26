package br.com.logistreams.core.usecase.inventory;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.application.core.usecase.inventory.CreateInventoryUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
        String inventoryName = "Duplicate Inventory Name";

        when(inventoryRepository.existsByName(inventoryName)).thenReturn(true);

        assertThrows(BusinessLogicException.class, () -> createInventoryUseCase.execute(inventoryName));

        verify(inventoryRepository, times(1)).existsByName(inventoryName);
        verify(inventoryRepository, times(0)).saveNew(any(Inventory.class));
    }
}