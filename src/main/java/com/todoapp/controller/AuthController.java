package com.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapp.model.LoginResponseDTO;
import com.todoapp.model.LoginRequestDTO;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        // TODO - add real database requests
        if ("user".equals(request.getUsername()) && "password".equals(request.getPassword())) {
            return ResponseEntity.ok(new LoginResponseDTO("fake-jwt-token"));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
