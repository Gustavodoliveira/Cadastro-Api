package com.example.cadastro.models;

import com.example.cadastro.Dtos.department.departmentRegister;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false)
  private Double salary;

  @Column(nullable = false)
  private String name;

  public Department(departmentRegister data) {
    this.name = data.name();
    this.salary = data.salary();
  }

}
