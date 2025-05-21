package com.example.emt2025main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * This class is used to configure user login on path '/login' and logout on path '/logout'.
 * The only public page in the application should be '/'.
 * All other pages should be visible only for a user with admin role.
 * Furthermore, in the "list.html" template, the 'Edit', 'Delete', 'Add' buttons should only be
 * visible for a user with librarian role.
 * <p>
 * For login inMemory users should be used. Their credentials are given below:
 * [{
 * username: "user",
 * password: "user",
 * role: "USER"
 * },
 * <p>
 * {
 * username: "librarian",
 * password: "librarian",
 * role: "LIBRARIAN"
 * }]
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public SecurityConfig(CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(requests -> requests.requestMatchers(
//                        "/",
//                        "api/user/login",
//                        "api/user/register"
//                ).permitAll().anyRequest().hasRole("LIBRARIAN"))
//                .formLogin((form) -> form.loginProcessingUrl(
//                                "/api/user/login")
//                        .permitAll()
//                        .failureUrl("/api/user/login?error=BadCredentials")
//                        .defaultSuccessUrl(
//                                "/swagger-ui/index.html",
//                                true
//                        ))
//                .logout((logout) -> logout.logoutUrl("/api/user/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/api/user/login"))
//                .exceptionHandling((ex) -> ex.accessDeniedPage(
//                        "/access_denied"));
//        return http.build();
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(
                        corsConfigurationSource()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
                AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        return authenticationManagerBuilder.build();
    }

}
