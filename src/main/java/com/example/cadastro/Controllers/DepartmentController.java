package com.example.cadastro.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping
  private ResponseEntity getAllDepartment() {
    try {
      List<Department> departments = this.departmentService.getAllDepartment();
      return ResponseEntity.ok().body(departments);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  private ResponseEntity getDepartmentById(@PathVariable String id) {
    try {
      Optional<Department> departmentOptional = this.departmentService.getDepartmentById(id);
      return ResponseEntity.ok().body(departmentOptional);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  private ResponseEntity deleteDepartmentById(@PathVariable String id) {
    try {
      String resp = this.departmentService.deleteDepartment(id);
      return ResponseEntity.ok().body(resp);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
