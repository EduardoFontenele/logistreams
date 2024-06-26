package br.com.logistreams.application.core.usecase.user;

import br.com.logistreams.application.adapters.web.exception.BusinessLogicException;
import br.com.logistreams.application.adapters.web.exception.ErrorsEnum;
import br.com.logistreams.application.core.domain.User;
import br.com.logistreams.application.core.ports.input.user.CreateUserInputPort;
import br.com.logistreams.application.core.repository.UserRepository;

public class CreateUserUseCase implements CreateUserInputPort {
    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void execute(User user) {
        if(userRepository.existsByName(user.getUsername())) {
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
        } else if(userRepository.isEmailRegistered(user.getEmail())) {
            throw new BusinessLogicException(ErrorsEnum.RESOURCE_ALREADY_EXISTS);
        }

        userRepository.createNew(user);
    }
}
