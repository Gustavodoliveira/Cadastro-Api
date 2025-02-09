package com.example.cadastro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastro.Models.User;

@Repository
public interface userRepository extends JpaRepository<User, String> {

}
