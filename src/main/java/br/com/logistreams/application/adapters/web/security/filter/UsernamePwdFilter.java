package br.com.logistreams.application.adapters.web.security.filter;

import br.com.logistreams.application.adapters.web.security.authentication.UsernamePwdAuthentication;
import br.com.logistreams.application.adapters.web.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class UsernamePwdFilter extends OncePerRequestFilter {
    private final CustomAuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader == null || authorizationHeader.equals("null")) {
            filterChain.doFilter(request, response);
            return;
        }

        String encodedUserCredentials = authorizationHeader.substring(6);
        byte[] decodedUserCredentialsBytes = Base64.getDecoder().decode(encodedUserCredentials);
        String decodedUserCredentialsString = new String(decodedUserCredentialsBytes);

        String[] parts = decodedUserCredentialsString.split(":");
        String username = parts[0];
        String password = parts[1];

        UsernamePwdAuthentication usernamePwdAuthentication = new UsernamePwdAuthentication(username, password, null);
        Authentication authentication = authenticationManager.authenticate(usernamePwdAuthentication);

        if(authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
