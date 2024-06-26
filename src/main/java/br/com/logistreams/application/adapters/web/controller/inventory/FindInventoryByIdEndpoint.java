package br.com.logistreams.application.adapters.web.controller.inventory;

import br.com.logistreams.application.adapters.web.dto.output.inventory.ListInventoryDTO;
import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.adapters.web.mapper.InventoryMapper;
import br.com.logistreams.application.core.domain.Inventory;
import br.com.logistreams.application.core.ports.input.inventory.ListInventoryByIdInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
public class FindInventoryByIdEndpoint {
    private final ListInventoryByIdInputPort listInventoryByIdInputPort;
    private final InventoryMapper inventoryMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ListInventoryDTO> findById(@PathVariable Long id) {
        if(id == null || id <= 0) {
            throw new BusinessLogicException(ErrorsEnum.INVALID_ID);
        }

        Inventory inventory = listInventoryByIdInputPort.execute(id);
        ListInventoryDTO response = inventoryMapper.toInventoryOutputDTO(inventory);

        return ResponseEntity.ok(response);
    }
}
