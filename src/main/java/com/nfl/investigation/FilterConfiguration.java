package com.nfl.investigation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class FilterConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        IdTokenAuthenticationFilter filter = new IdTokenAuthenticationFilter();
        JWTAuthorizationFilter filter = new JWTAuthorizationFilter(tokenAuthenticationManager);
        //filter.setAuthenticationManager(tokenAuthenticationManager);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

/*    @Bean
    public IdTokenAuthenticationFilter idTokenAuthenticationFilter() throws Exception {
        return new IdTokenAuthenticationFilter(authenticationManager());
    }*/
    //@Bean
/*    public AuthenticationManager authenticationManager() {
        return new TokenAuthenticationManager();
    }*/
}
