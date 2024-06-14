package br.com.logistreams.application.infrastructure.web.controller;

import br.com.logistreams.application.infrastructure.web.dto.input.InventoryInputDTO;
import br.com.logistreams.application.infrastructure.web.mapper.InventoryMapper;
import br.com.logistreams.domain.entity.Inventory;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {
    private final CreateInventoryInputPort createInventoryInputPort;
    private final InventoryMapper inventoryMapper;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createNewInventory(@RequestBody @Valid InventoryInputDTO inventoryInputDTO) {
        Inventory inventory = inventoryMapper.toDomain(inventoryInputDTO);
        createInventoryInputPort.execute(inventory);
        return ResponseEntity.ok().build();
    }
}
