package br.com.logistreams.application.core.ports.input.user;

import br.com.logistreams.application.core.domain.User;

public interface CreateUserInputPort {
    void execute(User user);
}
