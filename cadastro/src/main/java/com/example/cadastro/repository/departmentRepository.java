package com.example.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cadastro.models.Department;

import jakarta.transaction.Transactional;

@Repository
public interface departmentRepository extends JpaRepository<Department, String> {

  @Transactional
  @Modifying
  @Query("update Department d set d.name = ?1, d.salary = ?2  where d.id = ?3")
  Integer updateDepartment(String name, Double salary, String id);
}
