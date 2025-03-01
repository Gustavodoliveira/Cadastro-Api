package com.example.cadastro.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.cadastro.models.User;

@Service
public class TokenService {
  @Value("${api.secret.security}")
  private String secret;

  public String generateToken(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    try {
      String token = JWT.create()
          .withIssuer("cadastro")
          .withSubject(user.getEmail())
          .withExpiresAt(tokenExpirationTime())
          .sign(algorithm);
      return token;
    } catch (JWTCreationException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.require(algorithm)
          .withIssuer("cadastro")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  private Instant tokenExpirationTime() {
    return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
  }
}
