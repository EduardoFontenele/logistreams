package br.com.logistreams.config;

import br.com.logistreams.application.core.ports.input.user.CreateUserInputPort;
import br.com.logistreams.application.core.repository.UserRepository;
import br.com.logistreams.application.core.usecase.user.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    public CreateUserInputPort createUserInputPort(UserRepository userRepository) {
        return new CreateUserUseCase(userRepository);
    }
}
