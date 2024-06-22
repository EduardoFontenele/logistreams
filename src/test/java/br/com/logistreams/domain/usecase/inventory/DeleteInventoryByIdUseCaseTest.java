package br.com.logistreams.domain.usecase.inventory;

import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.domain.repository.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
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
import static org.mockito.Mockito.verifyNoInteractions;

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