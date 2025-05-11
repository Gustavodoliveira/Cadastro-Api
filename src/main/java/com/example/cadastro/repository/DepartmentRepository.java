package com.example.cadastro.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cadastro.Models.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {

}
