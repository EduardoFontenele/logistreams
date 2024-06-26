package br.com.logistreams.core.usecase.inventory;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.application.core.usecase.inventory.ListInventoryByIdUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ListInventoryByIdUseCaseTest {
    @Mock
    InventoryRepository inventoryRepository;

    @InjectMocks
    ListInventoryByIdUseCase listInventoryByIdUseCase;

    @Test
    @DisplayName("Given an existent inventory, when inventory repository is called, then return Product")
    void test1() {
        Inventory inventory = new Inventory(1L, "Camisas");

        BDDMockito.given(inventoryRepository.findById(1L)).willReturn(inventory);

        Inventory foundInventory = listInventoryByIdUseCase.execute(1L);

        assertThat(foundInventory).isNotNull();
        assertThat(foundInventory.getId()).isEqualTo(1L);
        assertThat(foundInventory.getName()).isEqualTo("Camisas");
        verify(inventoryRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Given a non existent inventory, when inventory is called, then throw business logic exception")
    void test2() {
        BDDMockito.given(inventoryRepository.findById(1L)).willReturn(null);

        BusinessLogicException result = assertThrows(BusinessLogicException.class, () -> listInventoryByIdUseCase.execute(1L));

        assertThat(result).isNotNull();
        assertThat(result.getHttpStatus()).isEqualTo(ErrorsEnum.RESOURCE_NOT_FOUND.getHttpStatus());
        assertThat(result.getErrorMessage()).isEqualTo(ErrorsEnum.RESOURCE_NOT_FOUND.getErrorMessage());
    }
}