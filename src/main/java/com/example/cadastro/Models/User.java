package com.example.cadastro.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id()
  @GeneratedValue(strategy = GenerationType.UUID)
  public String Id;

  @Column(length = 30, nullable = false)
  public String email;

  @Column(length = 30, nullable = false)
  public String userName;

  @Column(length = 30, nullable = false)
  public String password;

  @Column(nullable = false)
  public UserRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == role.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.getUsername();
  }
}
