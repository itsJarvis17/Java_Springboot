package com.itsjarvis.Spring.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable());// disable csrf
        http.authorizeHttpRequests(authorize->authorize.anyRequest().authenticated());// any request should be authenticated
        http.formLogin(Customizer.withDefaults());  // use default form login & can't be used when request is stateless
        http.httpBasic(Customizer.withDefaults()); // allows to pass user login creds through HTTP request header
       // http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
