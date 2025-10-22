package com.todoapp.model;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username;
    private String password;
}
