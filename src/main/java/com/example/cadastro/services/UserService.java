package com.example.cadastro.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.cadastro.Dtos.users.userRegisterDto;
import com.example.cadastro.Models.User;
import com.example.cadastro.repository.userRepository;

@Service
public class UserService {

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
      System.out.println(emailIsValid);
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
}
