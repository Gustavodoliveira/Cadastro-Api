package com.example.cadastro.Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro.Dtos.department.departmentRegister;
import com.example.cadastro.models.Department;
import com.example.cadastro.repository.departmentRepository;

@Service
public class departmentService {

  @Autowired
  private departmentRepository departmentRepository;

  public String saveDepartment(departmentRegister data) throws Exception {
    try {
      Department newDepartment = new Department(data);
      departmentRepository.save(newDepartment);
      return "Success create new Department";
    } catch (Exception e) {
      throw new Exception(e.getLocalizedMessage());
    }
  }

  public String deleteDepartment(String id) throws Exception {
    try {
      departmentRepository.deleteById(id);
      return "Department delete success";
    } catch (Exception e) {
      throw new Exception(e.getLocalizedMessage());
    }
  }
}
