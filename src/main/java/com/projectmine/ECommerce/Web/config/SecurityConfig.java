package com.projectmine.ECommerce.Web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())       // disable CSRF so POST from fetch() works
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/html/register.html",
                                "/html/login.html",
                                "/html/index.html",
                                "/html/account.html",
                                "/html/new.html",
                                "/html/electronics.html",
                                "/html/men.html",
                                "/html/women.html",
                                "/html/sale.html",
                                "/css/styles.css",
                                "/css/new.css",
                                "/css/electronics.css",
                                "/css/auth.css",
                                "/javascript/register.js",
                                "/javascript/login.js",
                                "/javascript/index.js",
                                "/javascript/account.js",
                                "/api/user/register",
                                "/api/user/login",
                                "/api/session-check",
                                "/api/logout"
                        ).permitAll()
                        .anyRequest().authenticated()   // everything else requires login
                )
                .formLogin(form -> form.disable())  // disable Spring's built-in login page
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
