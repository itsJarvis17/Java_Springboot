package com.itsjarvis.JarvisEcomWebsite.config;

import com.itsjarvis.JarvisEcomWebsite.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;
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

        http.csrf(customizer -> customizer.disable())// disable csrf
                .authorizeHttpRequests(authorize->authorize.requestMatchers("/login","/register")
                .permitAll().anyRequest().authenticated())// any request should be authenticated
        //http.formLogin(Customizer.withDefaults());  // use default form login & can't be used when request is stateless
                .httpBasic(Customizer.withDefaults()) // allows to pass user login creds through HTTP request header
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
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
