package com.todoapp;

import com.todoapp.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.todoapp.repository.UserRepository;

@SpringBootApplication
public class TodoBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder ) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {

                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("password"));
                userRepository.save(user);

                User user1 = new User();
                user1.setUsername("user1");
                user1.setPassword(encoder.encode("password1"));
                userRepository.save(user1);

            }
        };
    }

}
