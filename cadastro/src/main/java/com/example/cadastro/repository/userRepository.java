package com.example.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cadastro.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface userRepository extends JpaRepository<User, String> {
  public User findByEmail(String email);

  @Transactional
  @Modifying
  @Query("update User u set u.email = ?1, u.name= ?2, u.userName = ?3, u.password = ?4 where u.id = ?5")
  Integer updateUser(String email, String name, String userName, String password, String id);
}
