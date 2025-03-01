package com.example.cadastro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.user.userLoginDto;
import com.example.cadastro.Dtos.user.userRegisterDto;
import com.example.cadastro.Service.TokenService;
import com.example.cadastro.Service.userService;
import com.example.cadastro.models.User;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  TokenService tokenService;

  @Autowired
  userService userService;

  @PostMapping("/register")
  public ResponseEntity registerUser(@RequestBody @Validated userRegisterDto data) throws Exception {
    try {

      User usr = userService.saveUser(data);
      String token = tokenService.generateToken(usr);
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody @Validated userLoginDto data) throws Exception {
    var auth = userService.LoginUser(data);
    var token = tokenService.generateToken((User) auth.getPrincipal());
    return ResponseEntity.ok().body(token);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity deleteUser(@PathVariable String id) throws Exception {
    String resp = this.userService.deleteUser(id);
    return ResponseEntity.ok().body(resp);

  }
}
