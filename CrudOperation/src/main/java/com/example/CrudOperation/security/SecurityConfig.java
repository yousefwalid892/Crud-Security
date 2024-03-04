package com.example.CrudOperation.security;

import com.example.CrudOperation.filter.AuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {           //Configure all the Http security of our app.

    private final AuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {    //created to use Authentication filter
        http
                .authorizeHttpRequests(authRequest -> {
                    authRequest.requestMatchers("/login").permitAll();
                    authRequest.anyRequest().authenticated();
                });
        http.sessionManagement(s ->
                s.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Session state shouldn`t be stored because we check on each request & this will help us ensure each request should be authenticated.
        );
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //execute this filter before UsernamePassword authentication
        return http.build();
    }
}
