package br.com.logistreams.application.infrastructure.web.controller;

import br.com.logistreams.application.infrastructure.persistence.jpa.entity.InventoryEntity;
import br.com.logistreams.application.infrastructure.persistence.jpa.repository.InventoryRepository;
import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.application.infrastructure.web.exception.BusinessLogicException;
import br.com.logistreams.application.infrastructure.web.exception.ErrorsEnum;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.fixtures.InventoryFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@Transactional
class InventoryControllerIntegrationTest {
    @Autowired
    private InventoryController inventoryController;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    @DisplayName("When called createNewInventory with valid inventory, should return ResponseEntity with 200")
    void test1() {
        InventoryInputDTO inventoryInputDTO = InventoryFixtures.gimmeValidInventoryInputDto();

        ResponseEntity<Void> responseEntity = inventoryController.createNewInventory(inventoryInputDTO);

        assertNotNull(responseEntity);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    @DisplayName("When called createNewInventory with existent inventory, should throw exception and return ResponseEntity with 400")
    void test2() {
        inventoryRepository.save(new InventoryEntity(null, "Camisetas", new HashSet<>()));
        InventoryInputDTO inventoryInputDTO = new InventoryInputDTO("Camisetas");

        BusinessLogicException exception = assertThrows(BusinessLogicException.class, () -> {
            inventoryController.createNewInventory(inventoryInputDTO);
        });

        assertNotNull(exception);
        assertEquals(ErrorsEnum.RESOURCE_ALREADY_EXISTS.getErrorMessage(), exception.getErrorMessage());
        assertEquals(ErrorsEnum.RESOURCE_ALREADY_EXISTS.getHttpStatus(), exception.getHttpStatus());
    }
}