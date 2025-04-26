package com.example.cadastro.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

  @Id()
  @GeneratedValue(strategy = GenerationType.UUID)
  public String Id;

  @Column(length = 30, nullable = false)
  public String email;

  @Column(length = 30, nullable = false)
  public String userName;

  @Column(length = 30, nullable = false)
  public String password;
}
