package com.example.cadastro.models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cadastro.Dtos.user.userRegisterDto;

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

@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30, nullable = false, unique = true)
  private String userName;

  @Column(length = 30, nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column()
  private Roles roles;

  public User(userRegisterDto data) {
    this.name = data.name();
    this.email = data.email();
    this.password = data.password();
    this.userName = data.userName();
    this.roles = data.roles();
  }

  public User(userRegisterDto data, String encryptedPassword) {
    this.email = data.email();
    this.name = data.name();
    this.userName = data.userName();
    this.password = encryptedPassword;
    this.roles = data.roles();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.roles == Roles.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public String getUsername() {
    return this.userName;
  }
}
