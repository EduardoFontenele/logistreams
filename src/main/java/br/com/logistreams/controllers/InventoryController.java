package br.com.logistreams.controllers;

import br.com.logistreams.dtos.input.inventory.InventoryInputDTO;
import br.com.logistreams.dtos.output.PagedResponse;
import br.com.logistreams.dtos.output.inventory.InventoryOutputDTO;
import br.com.logistreams.services.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<PagedResponse<InventoryOutputDTO>> listInventories(
            @RequestParam(value = "page_size", required = false) Integer pageSize,
            @RequestParam(value = "page_number", required = false) Integer pageNumber
    ) {
        PagedResponse<InventoryOutputDTO> response = inventoryService.listAll(pageNumber, pageSize);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<InventoryOutputDTO> createNewInventory(@RequestBody InventoryInputDTO inventoryInputDTO) {
        inventoryService.createNewInventory(inventoryInputDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryById(@PathVariable int id) {
        inventoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryOutputDTO> getInventoryById(@PathVariable int id) {
        return ResponseEntity.ok(inventoryService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InventoryOutputDTO> patchInventory(@PathVariable int id, @RequestBody InventoryInputDTO inventoryInputDTO) {
        InventoryOutputDTO updatedInventory = inventoryService.updateInventory(id, inventoryInputDTO);
        return ResponseEntity.ok(updatedInventory);
    }
}
