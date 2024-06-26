package br.com.logistreams.core.usecase.inventory;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.application.core.usecase.inventory.DeleteInventoryByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class DeleteInventoryByIdUseCaseTest {
    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    DeleteInventoryByIdUseCase deleteInventoryByIdUseCase;

    @Test
    @DisplayName("Given an ID, when inventory is present, then delete inventory")
    void test1() {
        given(inventoryRepository.existsById(1L)).willReturn(true);

        deleteInventoryByIdUseCase.execute(1L);

        verify(inventoryRepository).existsById(1L);
        verify(inventoryRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Given an ID, when inventory is absent, then throw not found exception")
    void test2() {
        given(inventoryRepository.existsById(1L)).willReturn(false);

        BusinessLogicException exception = assertThrows(BusinessLogicException.class, () -> deleteInventoryByIdUseCase.execute(1L));

        assertNotNull(exception);
        assertEquals(ErrorsEnum.RESOURCE_NOT_FOUND.getHttpStatus(), exception.getHttpStatus());
        assertEquals(ErrorsEnum.RESOURCE_NOT_FOUND.getErrorMessage(), exception.getErrorMessage());
        verify(inventoryRepository).existsById(1L);
        verify(inventoryRepository, times(0)).deleteById(1L);
    }
}