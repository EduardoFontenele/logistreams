package br.com.logistreams.application.core.repository;

import br.com.logistreams.application.core.domain.User;

public interface UserRepository {
    void createNew(User user);
    boolean existsByName(String username);
    boolean isEmailRegistered(String email);
};
