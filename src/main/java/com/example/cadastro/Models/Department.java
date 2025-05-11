package com.example.cadastro.Models;

import java.util.UUID;

import com.example.cadastro.Dtos.departments.DepartmentRegisterDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Department")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(length = 50, nullable = false, unique = true)
  private String department;

  @Column(length = 50, nullable = false)
  private Float salary;

  public Department(DepartmentRegisterDto data) {
    this.department = data.department();
    this.salary = data.salary();
  }

}
