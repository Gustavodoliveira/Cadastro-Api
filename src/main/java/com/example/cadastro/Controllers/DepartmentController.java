package com.example.cadastro.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.departments.DepartmentRegisterDto;
import com.example.cadastro.Models.Department;
import com.example.cadastro.services.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @PostMapping("/register")
  private ResponseEntity registerDepartment(@RequestBody @Validated DepartmentRegisterDto data) {
    try {
      Department department = this.departmentService.saveDepartment(data);
      return ResponseEntity.ok().body("Create Department success");
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
