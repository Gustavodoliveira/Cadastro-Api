package com.example.cadastro.Dtos.user;

import com.example.cadastro.models.Roles;

public record userRegisterDto(String name, String userName, String email, String password, Roles roles) {

}
