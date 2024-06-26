package br.com.logistreams.core.usecase.inventory;

import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.application.core.usecase.inventory.ListInventoriesUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListInventoriesUseCaseTest {

    @Mock
    private InventoryRepository mockInventoryRepository;

    @Test
    @DisplayName("Should return a list of inventories from the repository")
    public void testListInventories_ReturnsInventoryList() {
        ListInventoriesUseCase useCase = new ListInventoriesUseCase(mockInventoryRepository);
        int pageNumber = 1;
        int pageSize = 10;
        List<Inventory> expectedInventories = new ArrayList<>();
        expectedInventories.add(new Inventory(1L, "Item 1"));
        expectedInventories.add(new Inventory(2L, "Item 2"));

        when(mockInventoryRepository.listInventories(pageNumber, pageSize)).thenReturn(expectedInventories);

        List<Inventory> actualInventories = useCase.execute(pageNumber, pageSize);

        assertEquals(expectedInventories, actualInventories);
    }
}