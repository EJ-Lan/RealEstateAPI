package com.codewithej.realestateapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security configuration class for the application.
 * <p>
 * Configures the security aspects of the application, such as authentication mechanisms,
 * password encoding, and specific security filters. This setup includes HTTP Basic authentication,
 * CSRF protection disabling, and an in-memory user details service for demonstration purposes.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the HTTP security for the application. It disables CSRF protection,
     * requires authentication for all requests, and enables HTTP Basic authentication.
     *
     * @param http The HttpSecurity to configure.
     * @return The SecurityFilterChain created from the HttpSecurity configuration.
     * @throws Exception if an error occurs during the configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .authorizeRequests(authz -> authz
                        .anyRequest().authenticated()) // Require authentication for all requests
                .httpBasic(withDefaults()); // Enable HTTP Basic authentication

        return http.build();
    }

    /**
     * Provides a BCryptPasswordEncoder bean to be used for encoding passwords
     * in the application, ensuring that passwords are securely stored.
     *
     * @return The password encoder to be used for encoding passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Specify the encoder to use for password encoding
    }

    /**
     * Configures an in-memory user details service with a single user for demonstration purposes.
     * This is not suitable for production use but provides a simple way to get started with authentication.
     *
     * @return An instance of UserDetailsService configured with a single user.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
