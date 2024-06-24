package br.com.logistreams.application.config;

import br.com.logistreams.domain.ports.input.user.CreateUserInputPort;
import br.com.logistreams.domain.usecase.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public CreateUserInputPort createUserInputPort() {
        return new CreateUserUseCase();
    }
}
