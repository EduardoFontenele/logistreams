package br.com.logistreams.application.adapters.web.security.providers;

import br.com.logistreams.application.adapters.persistence.jpa.entity.UserEntity;
import br.com.logistreams.application.adapters.persistence.jpa.repository.JpaUserRepository;
import br.com.logistreams.application.adapters.web.security.authentication.UsernamePwdAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final JpaUserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePwdAuthentication usernamePwdAuthentication = ((UsernamePwdAuthentication) authentication);
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(authentication.getName());

        if(userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            if(passwordEncoder.matches(usernamePwdAuthentication.getCredentials().toString(), userEntity.getPassword())) {
                UsernamePwdAuthentication authenticationResult = new UsernamePwdAuthentication(userEntity.getUsername(), null, new HashSet<>());
                authenticationResult.setAuthenticated(true);
                return authenticationResult;
            } else {
                throw new BadCredentialsException("Wrong password, please try again");
            }
        } else {
            throw new BadCredentialsException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePwdAuthentication.class);
    }
}
