package com.example.cadastro.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import com.example.cadastro.Dtos.department.departmentRegister;
import com.example.cadastro.models.Department;
import com.example.cadastro.repository.departmentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class departmentServiceTest {
  @Mock
  departmentService departmentService;

  @Autowired
  departmentRepository departmentRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should success save department")
  void saveDepartment() {
    departmentRegister dpt = new departmentRegister("admin", 1.500);

    Department departmentCreate = createDepartment(dpt);

    Optional<Department> result = departmentRepository.findById(departmentCreate.getId());

    assertThat(result.isPresent()).isTrue();

  }

  Department createDepartment(departmentRegister data) {
    Department newDp = new Department(data);
    departmentRepository.save(newDp);
    return newDp;
  }

}
