package com.example.cadastro.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.cadastro.repository.userRepository;
import com.example.cadastro.services.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterSecurity extends OncePerRequestFilter {
  @Autowired
  TokenService tokenService;

  @Autowired
  userRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    var token = this.recoverToken(request);

    if (token != null) {
      var email = tokenService.validateToken(token);
      UserDetails user = userRepository.findByEmail(email);

      var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);

    }
    filterChain.doFilter(request, response);

  }

  public String recoverToken(HttpServletRequest req) {

    var authHeader = req.getHeader("Authorization");

    if (authHeader == null)
      return null;
    return authHeader.replace("Bearer ", "");

  }
}
