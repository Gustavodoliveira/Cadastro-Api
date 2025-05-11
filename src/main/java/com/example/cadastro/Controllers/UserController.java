package com.example.cadastro.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.users.userLoginDto;
import com.example.cadastro.Dtos.users.userRegisterDto;
import com.example.cadastro.Dtos.users.userResponse;
import com.example.cadastro.Dtos.users.userUpdate;
import com.example.cadastro.Models.User;
import com.example.cadastro.repository.userRepository;
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

  @PostMapping("/login")
  public ResponseEntity loginUser(@RequestBody @Validated userLoginDto data) throws Exception {
    try {
      var auth = userService.LoginUser(data);
      var token = tokenService.generateToken((User) auth.getPrincipal());
      return ResponseEntity.ok().body(token);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping()
  private ResponseEntity getAllUser() throws Exception {
    try {
      List<User> usrs = userService.getAllUser();
      return ResponseEntity.ok().body(usrs);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  private ResponseEntity getUserById(@PathVariable String id) throws Exception {
    try {
      Optional<User> usr = userService.getUserById(id);
      return ResponseEntity.ok().body(usr);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  private ResponseEntity deleteUserById(@PathVariable String id) throws Exception {
    try {
      String resp = userService.deleteUser(id);
      return ResponseEntity.ok().body(resp);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  @GetMapping("/getUserByDepartment/{id}")
  private ResponseEntity getUsersByDepartment(@PathVariable String id) {
    try {
      List<User> usrs = this.userService.getUserByDepartmentId(id);
      return ResponseEntity.ok().body(usrs);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/update/{id}")
  private ResponseEntity updateUser(@PathVariable String id, @RequestBody @Validated userUpdate data) throws Exception {

    try {
      User resp = userService.updateUser(data, id);
      return ResponseEntity.ok(resp);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

}
