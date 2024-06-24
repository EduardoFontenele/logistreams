package br.com.logistreams.domain.ports.input.user;

import br.com.logistreams.domain.entity.User;

public interface CreateUserInputPort {
    void execute(User user);
}
