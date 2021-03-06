package br.com.felipezanella.housewatcher.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ApiAuthenticationManager implements AuthenticationManager {

    private String apiKeyValue;

    public ApiAuthenticationManager(String apiKeyValue) {
        this.apiKeyValue = apiKeyValue;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String principal = (String) authentication.getPrincipal();
        if (!apiKeyValue.equals(principal)) {
            throw new BadCredentialsException("The API key was not found or not the expected value.");
        }
        authentication.setAuthenticated(true);
        return authentication;
    }
}
