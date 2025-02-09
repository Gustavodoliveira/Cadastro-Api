package com.example.cadastro.Service;

import static org.mockito.Mockito.never;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cadastro.Dtos.user.userRegisterDto;
import com.example.cadastro.Models.User;
import com.example.cadastro.repository.userRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class userServiceTest {

  @Autowired
  userRepository repository;

  @Test
  @DisplayName("Should get user successfully from bd")
  void testSaveUser() {
    userRegisterDto data = new userRegisterDto(null, "gust", "email", "passowrd");
    User user = createUser(data);

    Optional<User> result = repository.findById(user.getId());

    assertThat(result.isPresent()).isTrue();
  }

  @Test
  @DisplayName("Should not get User from bd when user not exists")
  void findbyUser() {
    String id = "6cb0da48-6efc-41eb-9bb3-deec0ab9c627";
    Optional<User> result = this.repository.findById(id);

    assertThat(result.isEmpty()).isTrue();
  }

  private User createUser(userRegisterDto data) {
    User newUser = new User(data);
    repository.save(newUser);
    return newUser;
  }
}
