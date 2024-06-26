package br.com.logistreams.application.adapters.web.controller.inventory;

import br.com.logistreams.application.adapters.web.dto.input.inventory.CreateInventoryDTO;
import br.com.logistreams.application.core.ports.input.inventory.CreateInventoryInputPort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateInventoryEndpoint {
    private final CreateInventoryInputPort createInventoryInputPort;

    @PostMapping(value = "/v1/inventories", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createInventory(@RequestBody @Valid CreateInventoryDTO inventoryRequest) {
        createInventoryInputPort.execute(inventoryRequest.getName());

        return ResponseEntity.ok().build();
    }
}
