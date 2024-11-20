package com.digital.marketing.be.config;

import com.digital.marketing.be.controller.AuthenticationFilterImpl;
import com.digital.marketing.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                        (authorize) -> authorize
                                .requestMatchers("/swagger-ui.html").permitAll()
                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/users/login").permitAll()
                                .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable()).anonymous().disable();
        http.addFilterAfter(new AuthenticationFilterImpl(userService), LogoutFilter.class);
        SecurityFilterChain chain = http.build();
        return chain;
    }
}
