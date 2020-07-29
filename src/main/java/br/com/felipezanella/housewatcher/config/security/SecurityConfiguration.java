package br.com.felipezanella.housewatcher.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${api-key-header}")
    private String principalRequestHeader;

    @Value("${api-key-value}")
    private String principalRequestValue;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiAuthenticationManager apiAuthenticationManager = new ApiAuthenticationManager(principalRequestValue);

        ApiFilter filter = new ApiFilter(principalRequestHeader);
        filter.setAuthenticationManager(apiAuthenticationManager);

        http.antMatcher("/**").csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
    }
}

