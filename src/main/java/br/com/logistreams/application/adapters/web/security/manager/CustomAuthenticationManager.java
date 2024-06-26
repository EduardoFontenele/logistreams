package br.com.logistreams.application.adapters.web.security.manager;

import br.com.logistreams.application.adapters.web.security.providers.UsernamePwdAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UsernamePwdAuthenticationProvider usernamePwdAuthenticationProvider;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(usernamePwdAuthenticationProvider.supports(authentication.getClass())) {
            return usernamePwdAuthenticationProvider.authenticate(authentication);
        }
        return authentication;
    }
}
