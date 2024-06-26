package br.com.logistreams.config;

import br.com.logistreams.application.core.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.application.core.ports.input.inventory.DeleteInventoryByIdInputPort;
import br.com.logistreams.application.core.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.application.core.ports.input.inventory.ListInventoryByIdInputPort;
import br.com.logistreams.application.core.repository.InventoryRepository;
import br.com.logistreams.application.core.usecase.inventory.CountInventoriesUseCase;
import br.com.logistreams.application.core.usecase.inventory.CreateInventoryUseCase;
import br.com.logistreams.application.core.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.application.core.usecase.inventory.DeleteInventoryByIdUseCase;
import br.com.logistreams.application.core.usecase.inventory.ListInventoriesUseCase;
import br.com.logistreams.application.core.usecase.inventory.ListInventoryByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    public CreateInventoryInputPort createInventoryInputPort(InventoryRepository inventoryRepository) {
        return new CreateInventoryUseCase(inventoryRepository);
    }

    @Bean
    public ListInventoriesInputPort listInventoriesInputPort(InventoryRepository inventoryRepository) {
        return new ListInventoriesUseCase(inventoryRepository);
    }

    @Bean
    public CountInventoriesInputPort countInventoriesInputPort(InventoryRepository inventoryRepository) {
        return new CountInventoriesUseCase(inventoryRepository);
    }

    @Bean
    public ListInventoryByIdInputPort listInventoryByIdInputPort(InventoryRepository inventoryRepository) {
        return new ListInventoryByIdUseCase(inventoryRepository);
    }

    @Bean
    public DeleteInventoryByIdInputPort deleteInventoryByIdInputPort(InventoryRepository inventoryRepository) {
        return new DeleteInventoryByIdUseCase(inventoryRepository);
    }
}
