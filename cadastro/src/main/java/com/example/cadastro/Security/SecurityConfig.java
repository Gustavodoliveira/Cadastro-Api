package com.example.cadastro.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private FilterSecurity FilterSecurity;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.POST, "/user/register").permitAll()
            .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/user/update/{id}").permitAll()
            .requestMatchers(HttpMethod.POST, "/department/register").hasRole("ADMIN")
            .requestMatchers(HttpMethod.GET, "/department/{id}").permitAll()
            .requestMatchers(HttpMethod.GET, "/department").permitAll()
            .requestMatchers(HttpMethod.PATCH, "/department/update/{id}").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/department/delete/{id}").hasRole("ADMIN")
            .anyRequest().authenticated())
        .addFilterBefore(FilterSecurity, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
