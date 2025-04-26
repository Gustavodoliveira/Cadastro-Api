package com.example.cadastro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.users.userRegisterDto;
import com.example.cadastro.Models.User;
import com.example.cadastro.repository.userRepository;

@Service
public class UserService {

  @Autowired
  userRepository userRepository;

  public String registerUser(userRegisterDto data) throws Exception {
    try {
      String passwordEncrypted = new BCryptPasswordEncoder().encode(data.password());
      User newUser = new User(data, passwordEncrypted);
      userRepository.save(newUser);
      return "success creating user";
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

}
