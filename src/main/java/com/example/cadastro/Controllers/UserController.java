package com.example.cadastro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.users.userRegisterDto;
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
      String resp = userService.registerUser(data);
      return ResponseEntity.ok().body(resp);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
