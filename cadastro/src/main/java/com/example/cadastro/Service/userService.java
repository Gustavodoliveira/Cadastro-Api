package com.example.cadastro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.user.userRegisterDto;
import com.example.cadastro.models.User;
import com.example.cadastro.repository.userRepository;

@Service
public class userService {

  @Autowired
  private userRepository repository;

  public String saveUser(userRegisterDto data) throws Exception {
    try {
      this.verifyFieldsNotNull(data);
      User newUser = new User(data);

      repository.save(newUser);
      return "save";
    } catch (Exception e) {
      throw new Exception(e);
    }

  }

  public boolean verifyFieldsNotNull(userRegisterDto data) throws Exception {
    if (data.email() == null)
      throw new Exception("Email cannot be null");
    if (data.name() == null)
      throw new Exception("Name cannot be null");
    if (data.userName() == null)
      throw new Exception("user Name cannot be null");
    if (data.password() == null)
      throw new Exception("Password cannot be null");
    return true;
  }
}
