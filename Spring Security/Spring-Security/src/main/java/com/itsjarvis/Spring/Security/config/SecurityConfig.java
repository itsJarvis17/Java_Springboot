package com.itsjarvis.Spring.Security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationProvider authProvider() {

        // Create instance of who provides authentication in this case database
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();

        // pass userdetails service instance to dao authentication provider to check
        daoAuthProvider.setUserDetailsService(userDetailsService);
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        // return authentication provider instance
        return daoAuthProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(customizer -> customizer.disable());// disable csrf
        http.authorizeHttpRequests(authorize->authorize.requestMatchers("/login","/register").permitAll().anyRequest().authenticated());// any request should be authenticated
        http.formLogin(Customizer.withDefaults());  // use default form login & can't be used when request is stateless
        http.httpBasic(Customizer.withDefaults()); // allows to pass user login creds through HTTP request header
       // http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    // Working with multiple in memory users
//    @Bean
//    public UserDetailsService createUsers() {
//        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("user@123").roles("USER").build();
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin@123").roles("ADMIN").build();
//
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }
}
