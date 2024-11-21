package com.itsjarvis.Spring.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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

    // Working with multiple in memory users
    @Bean
    public UserDetailsService createUsers() {
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user@123").roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin@123").roles("ADMIN").build();


        return new InMemoryUserDetailsManager(user, admin);
    }
}
