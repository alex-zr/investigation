package com.nfl.investigation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.nfl.investigation.Constants.*;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(HEADER_STRING);
        if (token == null)
            token = request.getParameter(HEADER_STRING);
        if (token == null) {
            JwtAuthenticationToken authentication = new JwtAuthenticationToken(null, null, false, null);
            authentication.setAuthenticated(false);
            chain.doFilter(request, res);
        }

        String subject = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        Map<String, Map<String, List<String>>> claimsMap = JsonUtils.readValue(subject, Map.class);
        Map<String, List<String>> rolesMap = claimsMap.get(ROLES_PREFIX);
        if (rolesMap == null) {
            chain.doFilter(request, res);
            return;
        }

        List<String> roles = rolesMap.get(CONSOLE_NAME);
        if (roles == null) {
            chain.doFilter(request, res);
            return;
        }

        Collection<GrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = new UserDetailsImpl(authorities, "name", "pass", true,
                true, true, true);

        JwtAuthenticationToken authentication = new JwtAuthenticationToken("authToken", authorities, true, userDetails);
        getAuthenticationManager().authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, res);
    }



}
