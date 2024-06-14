package br.com.logistreams.application.config;

import br.com.logistreams.domain.usecase.inventory.CreateInventoryUseCase;
import br.com.logistreams.domain.ports.input.inventory.CreateInventoryInputPort;
import br.com.logistreams.domain.ports.output.inventory.CreateInventoryOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InventoryConfig {

    @Bean
    public CreateInventoryInputPort createInventoryInputPort(CreateInventoryOutputPort createInventoryOutputPort) {
        return new CreateInventoryUseCase(createInventoryOutputPort);
    }
}
