package com.example.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastro.models.Department;

@Repository
public interface departmentRepository extends JpaRepository<Department, String> {

}
