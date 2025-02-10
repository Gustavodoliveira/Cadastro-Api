package com.example.cadastro.models;

public enum Roles {
  ADMIN("admin"),
  USER("user");

  private String role;

  Roles(String roles) {
    this.role = roles;
  }
}
