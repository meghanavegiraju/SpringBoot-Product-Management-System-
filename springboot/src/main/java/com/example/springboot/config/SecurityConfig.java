package com.example.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Define in-memory user with encrypted password
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.withUsername("meghana")
                               .password(encoder.encode("password123"))
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }

    // Define password encoder (BCrypt)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (not recommended in prod)

            .authorizeHttpRequests(auth -> auth
                // Allow public access to login page and static files
                .requestMatchers("/login.html", "/css/**", "/js/**").permitAll()

                // All other requests require authentication
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginPage("/login.html")                 // Custom login page
                .loginProcessingUrl("/login")             // Spring handles POST /login
                .defaultSuccessUrl("/products", true)     // Redirect here on successful login
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html")
            );

        return http.build();
    }
}
