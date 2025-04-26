package com.example.cadastro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.users.userRegisterDto;
import com.example.cadastro.Dtos.users.userResponse;
import com.example.cadastro.Models.User;
import com.example.cadastro.services.TokenService;
import com.example.cadastro.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private TokenService tokenService;

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  private ResponseEntity registerUser(@Validated @RequestBody userRegisterDto data) {
    try {
      User user = userService.registerUser(data);
      String token = tokenService.generateToken(user);
      userResponse userResponse = new userResponse("success creating user", token);
      return ResponseEntity.ok().body(userResponse);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
