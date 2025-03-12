package com.example.cadastro.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.crypto.spec.DESKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.cadastro.Dtos.department.departmentRegister;
import com.example.cadastro.Dtos.department.updateDepartment;
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

  public List<Department> getAllDepartment() throws Exception {
    try {
      List<Department> dptAll = departmentRepository.findAll();
      return dptAll;
    } catch (Exception e) {
      throw new Exception(e.getLocalizedMessage());
    }
  }

  public Optional<Department> getDepartment(String id) throws Exception {
    try {
      Optional<Department> dpt = departmentRepository.findById(id);
      return dpt;
    } catch (Exception e) {
      throw new Exception(e.getLocalizedMessage());
    }
  }

  public String updateDepartment(updateDepartment data, String id) throws Exception {
    try {
      departmentRepository.updateDepartment(data.name(), data.salary(), id);
      return "Update Success";
    } catch (Exception e) {
      throw new Exception(e.getLocalizedMessage());
    }
  }
}
