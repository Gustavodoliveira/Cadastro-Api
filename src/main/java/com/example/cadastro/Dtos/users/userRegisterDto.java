package com.example.cadastro.Dtos.users;

import com.example.cadastro.Models.Department;
import com.example.cadastro.Models.UserRole;

public record userRegisterDto(String email, String userName, String password, Department department, UserRole role) {

}
