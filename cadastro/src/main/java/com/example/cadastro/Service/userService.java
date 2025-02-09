package com.example.cadastro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.user.userRegisterDto;
import com.example.cadastro.Models.User;
import com.example.cadastro.repositories.userRepository;

@Service
public class userService {

  @Autowired
  private userRepository repository;

  public String saveUser(userRegisterDto data) throws Exception {
    try {
      User newUser = new User(data);
      repository.save(newUser);
      return "save";
    } catch (Exception e) {
      throw new Exception(e);
    }

  }
}
