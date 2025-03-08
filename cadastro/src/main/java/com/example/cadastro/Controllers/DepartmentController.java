package com.example.cadastro.Controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.department.departmentRegister;
import com.example.cadastro.Service.departmentService;
import com.example.cadastro.repository.departmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private departmentService departmentService;

  @PostMapping("/register")
  private ResponseEntity saveDepartment(@RequestBody departmentRegister data) {
    try {
      String result = departmentService.saveDepartment(data);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/delete/{id}")
  private ResponseEntity deleteDepartment(@PathVariable String id) {
    try {
      String result = departmentService.deleteDepartment(id);
      return ResponseEntity.ok(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
