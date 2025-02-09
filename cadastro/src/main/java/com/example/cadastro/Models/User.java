package com.example.cadastro.Models;

import jakarta.annotation.Generated;
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
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
