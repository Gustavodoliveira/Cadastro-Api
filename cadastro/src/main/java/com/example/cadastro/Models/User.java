package com.example.cadastro.Models;

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
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  public String id;

  @Column(length = 30, nullable = false)
  private String name;

  @Column(length = 30, nullable = false, unique = true)
  private String userName;

  @Column(length = 30, nullable = false, unique = true)
  private String email;

  @Column(nullable = false, unique = true)
  private String password;

  public User(userRegisterDto data) {
    this.name = data.name();
    this.email = data.email();
    this.password = data.password();
    this.userName = data.userName();
  }
}
