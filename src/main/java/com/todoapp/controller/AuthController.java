package com.todoapp.controller;

import com.todoapp.model.RegisterRequestDTO;
import com.todoapp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.todoapp.model.LoginResponseDTO;
import com.todoapp.model.LoginRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.todoapp.repository.UserRepository;

import java.util.Map;


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
        return userRepository.findByUsername(request.getUsername())
                .filter(user -> passwordEncoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok(new LoginResponseDTO("fake-jwt-token"))) // // TODO Replace with real JWT token
                .orElse(ResponseEntity.status(401).build());
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterRequestDTO request) {

        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            System.out.println("Username is not available");
            return ResponseEntity.badRequest().body(Map.of("error", "Username is not available"));
        }

        System.out.println("Username is available");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        System.out.println("Request successfully " + request.getUsername() + " password: " + passwordEncoder.encode(request.getPassword()));
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
}
