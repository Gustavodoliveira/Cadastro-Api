package com.example.cadastro.Controllers;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro.Dtos.department.departmentRegister;
import com.example.cadastro.Dtos.department.updateDepartment;
import com.example.cadastro.Service.departmentService;
import com.example.cadastro.models.Department;
import com.example.cadastro.repository.departmentRepository;

@RestController
@RequestMapping("/department")
public class DepartmentController {

  @Autowired
  private departmentService departmentService;

  @GetMapping()
  private ResponseEntity getAllDepartment() {
    try {
      List<Department> dptAll = departmentService.getAllDepartment();
      return ResponseEntity.ok().body(dptAll);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }
  }

  @GetMapping("/{id}")
  private ResponseEntity getDepartmentById(@PathVariable String id) {
    try {
      Optional<Department> dpt = departmentService.getDepartment(id);
      return ResponseEntity.ok().body(dpt);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getLocalizedMessage());
    }
  }

  @PostMapping("/register")
  private ResponseEntity saveDepartment(@RequestBody departmentRegister data) {
    try {
      String result = departmentService.saveDepartment(data);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/update/{id}")
  private ResponseEntity updateDepartment(@PathVariable String id, @RequestBody updateDepartment data) {
    try {
      String res = departmentService.updateDepartment(data, id);
      return ResponseEntity.ok().body(res);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getLocalizedMessage());
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
