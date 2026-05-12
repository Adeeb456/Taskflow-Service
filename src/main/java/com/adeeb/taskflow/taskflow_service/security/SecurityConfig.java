package com.adeeb.taskflow.taskflow_service.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                // CSRF disabled for simplified REST API interaction
                // and H2 console access during development.
                .csrf(csrf -> csrf.disable())

                // Required for rendering H2 console inside browser iframe.
                .headers(headers ->
                        headers.frameOptions(frame ->
                                frame.sameOrigin()
                        )
                )

                .authorizeHttpRequests(auth ->
                        auth
                                // Public routes
                                .requestMatchers(
                                        "/",
                                        "/index.html",
                                        "/h2-console/**"
                                )
                                .permitAll()

                                // Secure only API endpoints
                                .requestMatchers("/api/**")
                                .authenticated()

                                .anyRequest()
                                .permitAll()
                )

                .httpBasic(Customizer.withDefaults())

                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService(
            @Value("${app.security.username}") String username,
            @Value("${app.security.password}") String password
    ){

        // In-memory authentication setup for development
        // Use encrypted passwords in production
        UserDetails userDetails = User.builder()
                .username(username)
                .password("{noop}" + password)
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }
}