package com.example.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastro.models.User;

@Repository
public interface userRepository extends JpaRepository<User, String> {
  public User findByEmail(String email);
}
