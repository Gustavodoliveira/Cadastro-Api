package com.example.cadastro.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.cadastro.Dtos.users.userLoginDto;
import com.example.cadastro.Dtos.users.userRegisterDto;
import com.example.cadastro.Dtos.users.userUpdate;
import com.example.cadastro.Models.User;
import com.example.cadastro.repository.userRepository;
import com.example.cadastro.security.FilterSecurity;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

  @Autowired
  HttpServletRequest req;

  @Autowired
  FilterSecurity filterSecurity;

  @Autowired
  TokenService tokenService;

  @Autowired
  private userRepository repository;

  @Autowired
  private AuthenticationManager authenticationManager;

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

  @Autowired
  userRepository userRepository;

  public User registerUser(userRegisterDto data) throws Exception {
    boolean emailIsValid = this.EmailIsValid(data.email());
    if (emailIsValid == false)
      throw new Exception("Your e-mail is invalid, try again");
    try {
      String passwordEncrypted = new BCryptPasswordEncoder().encode(data.password());
      User newUser = new User(data, passwordEncrypted);
      userRepository.save(newUser);
      return newUser;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public List<User> getAllUser() throws Exception {
    try {
      return userRepository.findAll();
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public static boolean EmailIsValid(String email) {
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public Optional<User> getUserById(String id) throws Exception {
    try {
      Optional<User> usr = userRepository.findById(id);
      return usr;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public List<User> getUserByDepartmentId(String id) throws Exception {
    try {
      List<User> usrs = this.userRepository.findUserByDepartmentId(id);
      return usrs;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public String deleteUser(String id) throws Exception {
    try {
      userRepository.deleteById(id);
      return "user delete success";
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public User updateUser(userUpdate data, String id) throws Exception {
    Optional<User> user = userRepository.findById(id);

    String email = user.orElseThrow().getEmail();
    User userUpdate = new User();

    userUpdate.setId(user.orElseThrow().getId());
    userUpdate.setRole(user.orElseThrow().getRole());

    if (data.email() == null)
      userUpdate.setEmail(user.orElseThrow().getEmail());
    else
      userUpdate.setEmail(data.email());

    if (data.userName() == null)
      userUpdate.setUserName(user.orElseThrow().getUsername());
    else
      userUpdate.setUserName(data.userName());

    if (data.password() == null)
      userUpdate.setPassword(user.orElseThrow().getPassword());
    else
      userUpdate.setPassword(data.password());

    String encryptedPassword = new BCryptPasswordEncoder().encode(userUpdate.getPassword());

    repository.updateUser(userUpdate.getEmail(), userUpdate.getUsername(),
        encryptedPassword, email);

    return userUpdate;

  }

  public Authentication LoginUser(userLoginDto data) throws Exception {

    var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
    var auth = this.authenticationManager.authenticate(userNamePassword);
    return auth;

  }
}
