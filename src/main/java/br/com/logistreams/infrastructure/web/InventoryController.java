package br.com.logistreams.infrastructure.web;

import br.com.logistreams.application.dto.in.InventoryInputDTO;
import br.com.logistreams.application.usecase.CreateInventoryUseCase;
import br.com.logistreams.domain.model.Inventory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    // O que eu vou fazer é o seguinte:
    // UseCase vão ser interfaces, contratos de como o método deve se portar.
    // Como não tem saída da aplicação pro mundo externo, só persistência, não vai ter adapter de saída.
    // Aqui no controller nós vamos usar um mapper pra transformar o DTO em classe de dominio
    // Do controller vai pro usecase (implementado como adapter), do adapter vai pro
    private final CreateInventoryUseCase createInventoryUseCase;

    @PostMapping
    public ResponseEntity<Void> createNewInventory(@RequestBody InventoryInputDTO inventoryInputDTO) {
        Inventory inventory = new Inventory();
        createInventoryUseCase.execute(inventory);
        return null;
    }
}
