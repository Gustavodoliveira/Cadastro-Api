package com.example.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cadastro.Models.User;

import jakarta.transaction.Transactional;

@Repository
public interface userRepository extends JpaRepository<User, String> {
  public User findByEmail(String email);

  @Transactional
  @Modifying
  @Query("update User u set u.email = ?1, u.userName = ?2, u.password = ?3 where u.email = ?4")
  Integer updateUser(String email, String userName, String password, String beforeEmail);

}
