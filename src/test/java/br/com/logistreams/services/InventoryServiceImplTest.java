package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.entities.Inventory;
import br.com.logistreams.mappers.InventoryMapper;
import br.com.logistreams.repositories.InventoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class InventoryServiceImplTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private InventoryMapper inventoryMapper;

    @InjectMocks
    private InventoryServiceImpl inventoryService;

    @Test
    @DisplayName("Given valid input inventory DTO, when save new entity on repository, then should call repository.save")
    void givenValidInventoryInputDTO_whenSaveNewEntity_thenRepositoryShouldBeCalled() {
        //given
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO("Camisas");
        Inventory inventory = new Inventory(1, "Camisas", new HashSet<>());
        given(inventoryMapper.toInventory(inventoryInputDTO)).willReturn(inventory);

        //when
        inventoryService.createNewInventory(inventoryInputDTO);

        //then
        verify(inventoryMapper, times(1)).toInventory(inventoryInputDTO);
        verify(inventoryRepository, times(1)).save(inventory);
    }
}