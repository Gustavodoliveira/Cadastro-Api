package com.example.cadastro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.user.userLoginDto;
import com.example.cadastro.Dtos.user.userRegisterDto;
import com.example.cadastro.Security.FilterSecurity;
import com.example.cadastro.models.User;
import com.example.cadastro.repository.userRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class userService {

  @Autowired
  HttpServletRequest req;

  @Autowired
  FilterSecurity filterSecurity;

  @Autowired
  TokenService tokenService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private userRepository repository;

  public User saveUser(userRegisterDto data) throws Exception {
    try {
      this.verifyFieldsNotNull(data);
      String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
      User newUser = new User(data, encryptedPassword);

      repository.save(newUser);
      return newUser;
    } catch (Exception e) {
      throw new Exception(e);
    }

  }

  public Authentication LoginUser(userLoginDto data) {
    var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);

    return auth;
  }

  // public User updateUser(UpdateUserDto data) {
  // String token = filterSecurity.recoverToken(this.req);
  // String userName = tokenService.validateToken(token);
  // User user = repository.findByEmail(userName);
  // User userUpdate = new User();

  // userUpdate.setRole(user.getRole());

  // if (data.email().isEmpty())
  // userUpdate.setEmail(user.getEmail());
  // else
  // userUpdate.setEmail(data.email());

  // if (data.userName() == null)
  // userUpdate.setUserName(user.getUsername());
  // else
  // userUpdate.setUserName(data.userName());

  // if (data.newPassword().isEmpty())
  // userUpdate.setPassword(user.getPassword());
  // else
  // userUpdate.setPassword(data.newPassword());

  // repository.updateUser(userUpdate.getEmail(), userUpdate.getUsername(),
  // userUpdate.getPassword(),
  // userName);

  // return userUpdate;
  // }

  public String deleteUser(String id) throws Exception {
    try {

      repository.deleteById(id);
      return "User delete success";
    } catch (Exception e) {
      throw new Exception(e.getMessage());
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
