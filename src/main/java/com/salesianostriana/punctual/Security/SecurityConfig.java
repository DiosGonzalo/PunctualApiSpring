package com.salesianostriana.punctual.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar para poder hacer POST desde Postman
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // ⚠️ PELIGRO: Esto deja entrar a todo el mundo (Solo Dev)
                );
        return http.build();
    }
}
