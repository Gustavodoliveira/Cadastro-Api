package com.example.cadastro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.departments.DepartmentRegisterDto;
import com.example.cadastro.Models.Department;
import com.example.cadastro.repository.DepartmentRepository;

@Service
public class DepartmentService {
  @Autowired
  private DepartmentRepository departmentRepository;

  public Department saveDepartment(DepartmentRegisterDto data) throws Exception {
    try {
      Department newDpt = new Department(data);
      departmentRepository.save(newDpt);
      return newDpt;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Optional<Department> getDepartmentById(String id) throws Exception {
    try {
      Optional<Department> department = this.departmentRepository.findById(id);
      return department;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public List<Department> getAllDepartment() throws Exception {
    try {
      List<Department> departments = this.departmentRepository.findAll();
      return departments;
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }
}
