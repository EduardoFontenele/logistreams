package br.com.logistreams.application.adapters.web.controller.inventory;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.ports.input.inventory.DeleteInventoryByIdInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/inventories")
public class DeleteInventoryEndpoint {
    private final DeleteInventoryByIdInputPort deleteInventoryByIdInputPort;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if(id == null || id <= 0) {
            throw new BusinessLogicException(ErrorsEnum.INVALID_ID);
        }

        deleteInventoryByIdInputPort.execute(id);
        return ResponseEntity.noContent().build();
    }
}
