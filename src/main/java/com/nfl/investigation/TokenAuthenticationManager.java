package com.nfl.investigation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class TokenAuthenticationManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
                authentication.setAuthenticated(true);
                return authentication;
        } catch (Exception ex) {
                throw ex;
        }
    }
}
