package br.com.logistreams.services;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.entities.Inventory;
import br.com.logistreams.mappers.InventoryMapper;
import br.com.logistreams.repositories.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

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
    @DisplayName("Given valid input inventory DTO, when save new entity on repository, then should persist an inventory in the database")
    void test1() {
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

    @Test
    @DisplayName("Given a call to findAll without page size and number, should return a list of inventory dto")
    void test2() {
        //given
        Inventory inventory = new Inventory(1, "Camisas", new HashSet<>());
        InventoryOutputDTO inventoryOutputDTO = new InventoryOutputDTO(1,"Camisas", new HashSet<>());
        List<Inventory> inventoryList = List.of(inventory);
        List<InventoryOutputDTO> inventoryOutputDTOList = List.of(inventoryOutputDTO);
        PageRequest pageRequest = PageRequest.of(0, 5);
        PageImpl<Inventory> inventoryPage = new PageImpl<>(inventoryList, pageRequest, 1);

        //when
        given(inventoryRepository.findAll(pageRequest)).willReturn(inventoryPage);
        given(inventoryMapper.toInventoryOutputDTOList(inventoryList)).willReturn(inventoryOutputDTOList);

        List<InventoryOutputDTO> result = inventoryService.listAll(1, 5);

        //then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(inventoryOutputDTO, result.get(0));
        assertEquals("Camisas", result.get(0).getName());
    }

    @Test
    @DisplayName("Given a valid inventory ID, when delete by id, should successfully delete inventory from database")
    void test3() {
        given(inventoryRepository.findById(1)).willReturn(Optional.of(new Inventory()));

        inventoryService.deleteById(1);

        verify(inventoryRepository, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Given an invalid inventory ID, when delete by id, should not delete anything")
    void testInvalidDelete() {
        given(inventoryRepository.findById(1)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> inventoryService.deleteById(1));

        verify(inventoryRepository, times(0)).deleteById(1);
    }

    @Test
    @DisplayName("Given a valid inventory ID, should call the repository and return an inventory")
    void test4() {
        //given
        Inventory inventory = new Inventory(1, "Camisas", new HashSet<>());
        InventoryOutputDTO inventoryOutputDTO = new InventoryOutputDTO(1, "Camisas", new HashSet<>());

        //when
        given(inventoryRepository.findById(1)).willReturn(Optional.of(inventory));
        given(inventoryMapper.toInventoryOutputDTO(inventory)).willReturn(inventoryOutputDTO);

        //then
        InventoryOutputDTO result = inventoryService.findById(1);

        assertNotNull(result);
        assertEquals("Camisas", result.getName());
        assertEquals(0, result.getSections().size());
    }

    @Test
    @DisplayName("Given an invalid inventory ID, should throw EntityNotFoundException")
    void testInvalidFindById() {
        given(inventoryRepository.findById(1)).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> inventoryService.findById(1));
    }

    @Test
    @DisplayName("Given valid inventory ID and partial update, when updateInventory, then should return updated inventory")
    void updateInventory_Success() {
        // given
        int inventoryId = 1;
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO();
        inventoryInputDTO.setName("Updated Name");

        Inventory existingInventory = new Inventory();
        existingInventory.setId(inventoryId);
        existingInventory.setName("Old Name");

        Inventory updatedInventory = new Inventory();
        updatedInventory.setId(inventoryId);
        updatedInventory.setName("Updated Name");

        InventoryOutputDTO updatedInventoryDTO = new InventoryOutputDTO(1, "Updated Name", new HashSet<>());

        given(inventoryRepository.findById(inventoryId)).willReturn(Optional.of(existingInventory));
        given(inventoryRepository.save(existingInventory)).willReturn(updatedInventory);
        given(inventoryMapper.toInventoryOutputDTO(existingInventory)).willReturn(updatedInventoryDTO);

        // when
        InventoryOutputDTO result = inventoryService.updateInventory(inventoryId, inventoryInputDTO);

        // then
        verify(inventoryRepository, times(1)).findById(inventoryId);
        verify(inventoryRepository, times(1)).save(existingInventory);
        verify(inventoryMapper, times(1)).toInventoryOutputDTO(existingInventory);

        assertEquals("Updated Name", result.getName());
    }

    @Test
    @DisplayName("Given invalid inventory ID, when updateInventory, then should throw EntityNotFoundException")
    void updateInventory_Failure() {
        // given
        int inventoryId = 999; // Assuming 999 does not exist
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO();
        inventoryInputDTO.setName("Updated Name");

        given(inventoryRepository.findById(inventoryId)).willReturn(Optional.empty());

        // when/then
        assertThrows(EntityNotFoundException.class, () -> {
            inventoryService.updateInventory(inventoryId, inventoryInputDTO);
        });

        // then
        verify(inventoryRepository, times(1)).findById(inventoryId);
        verify(inventoryRepository, times(0)).save(null);
        verify(inventoryMapper, times(0)).toInventoryOutputDTO(null);
    }
}