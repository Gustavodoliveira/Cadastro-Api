package com.example.cadastro.Models;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.cadastro.Dtos.users.userRegisterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

  @Column(nullable = false)
  public String password;

  @ManyToOne()
  public Department department;

  @Column(nullable = false)
  public UserRole role;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == role.ADMIN)
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
    else
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  public User(userRegisterDto data, String passwordEncrypted) {
    this.email = data.email();
    this.userName = data.userName();
    this.password = passwordEncrypted;
    this.role = data.role();
    this.department = data.department();

  }

  @Override
  public String getUsername() {
    return this.userName;
  }
}
