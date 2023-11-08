package com.C10G14.WorldFitBackend;

import com.C10G14.WorldFitBackend.repository.UserRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService(){

        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public OpenAPI customOpenAPI(){
        Contact contact = new Contact();

        contact.name("Gabriel, Diego and Emiliano").email("emilianoescobedo9@gmail.com");

        return new OpenAPI()
                .info(new Info()
                        .title("World Fit REST API")
                        .version("0.1")
                        .description("Application to manage flow of activities in a gym. The main features it includes are user registration and login, role-based access to resources, custom routines built with predetermined exercises, integrated payment system, and management and visualization tools for administrators.")
                        .termsOfService("https://swagger.io.terms")
                        .license(new License().name("Apache License, Version 2.0").url("http://www.apache.org/licenses/"))
                        .contact(contact));
    }
}