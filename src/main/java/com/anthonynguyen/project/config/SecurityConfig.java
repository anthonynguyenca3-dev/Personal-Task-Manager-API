package com.anthonynguyen.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                .requestMatchers("/api/tasks/**").authenticated().anyRequest().authenticated());

        return http.build();
    }


}
