package com.RobeOrtiz.videogames_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/videogame/add", "/videogame/save", "/developmentTeam/add", "/developmentTeam/save").authenticated() 
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
            	.loginPage("/login")
                .permitAll() 
            )
            .logout(logout -> logout
                .permitAll() 
            );
        
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //user and password are invented for the security of the aplication in deployment
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails userFalso1 = User.withUsername("falso1")
                .password(passwordEncoder().encode("falso"))
                .build();
        UserDetails userFalso2 = User.withUsername("falso2")
                .password(passwordEncoder().encode("falso"))
                .build();
        UserDetails userFalso3 = User.withUsername("falso3")
                .password(passwordEncoder().encode("falso"))
                .build();

        return new InMemoryUserDetailsManager(userFalso1,userFalso2,userFalso3);
    }
}
