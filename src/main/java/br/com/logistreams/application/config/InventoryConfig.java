package br.com.logistreams.application.config;

import br.com.logistreams.domain.repository.InventoryRepository;
import br.com.logistreams.domain.usecase.inventory.CreateInventoryUseCase;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    public CreateInventoryInputPort createInventoryInputPort(InventoryRepository inventoryRepository) {
        return new CreateInventoryUseCase(inventoryRepository);
    }
}
