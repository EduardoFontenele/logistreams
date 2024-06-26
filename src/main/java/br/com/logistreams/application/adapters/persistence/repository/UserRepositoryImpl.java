package br.com.logistreams.application.adapters.persistence.repository;

import br.com.logistreams.application.adapters.persistence.jpa.entity.UserEntity;
import br.com.logistreams.application.adapters.persistence.jpa.repository.JpaUserRepository;
import br.com.logistreams.application.core.domain.User;
import br.com.logistreams.application.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final PasswordEncoder passwordEncoder;
    private final JpaUserRepository jpaUserRepository;

    @Override
    public void createNew(User user) {
        UserEntity userEntity = new UserEntity(user.getUsername(), passwordEncoder.encode(user.getPassword()), user.getEmail());
        jpaUserRepository.save(userEntity);
    }

    @Override
    public boolean existsByName(String username) {
        return false;
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return false;
    }
}
