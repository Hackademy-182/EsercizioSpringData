package it.aulab.esercizio_spring_data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // ❌ Disabilita CSRF
            .csrf(csrf -> csrf.disable())

            // 🔓 Permetti tutte le richieste (per test)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // (opzionale) disabilita login form
            .httpBasic();

        return http.build();
    }
}