package br.com.logistreams.application.config;

import br.com.logistreams.domain.ports.input.inventory.CountInventoriesInputPort;
import br.com.logistreams.domain.ports.input.inventory.ListInventoriesInputPort;
import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.domain.usecase.inventory.CountInventoriesUseCase;
import br.com.logistreams.domain.usecase.inventory.CreateInventoryUseCase;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.domain.usecase.inventory.ListInventoriesUseCase;
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
}
