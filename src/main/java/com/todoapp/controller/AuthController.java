package com.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapp.model.LoginResponseDTO;
import com.todoapp.model.LoginRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.todoapp.repository.UserRepository;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        System.out.println("LOGIN ATTEMPT");
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok(new LoginResponseDTO("fake-jwt-token"))) // later replace with real JWT
                .orElse(ResponseEntity.status(401).build());
    }
}
