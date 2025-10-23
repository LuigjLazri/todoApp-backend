package com.todoapp;

import com.todoapp.model.TodoItem;
import com.todoapp.model.User;
import com.todoapp.repository.TodoRepository;
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
    CommandLineRunner init(UserRepository userRepository, PasswordEncoder encoder, TodoRepository todoRepository) {
        return args -> {
            if (userRepository.findByUsername("user").isEmpty()) {
                User user = new User();
                user.setUsername("user");
                user.setPassword(encoder.encode("password"));
                userRepository.save(user);

                System.out.println("CREATED USER");

                TodoItem item = new TodoItem();
                item.setUser(user);
                item.setTitle("Buy Battlefield 6");
                item.setDescription("Buy the new game");
                item.setStatus("normal");
                item.setCompleted(false);


                TodoItem item1 = new TodoItem();
                item1.setUser(user);
                item1.setTitle("Buy EA 26");
                item1.setDescription("Buy the new game");
                item1.setStatus("normal");
                item1.setCompleted(false);

                TodoItem item2 = new TodoItem();
                item2.setUser(user);
                item2.setTitle("Buy COD");
                item2.setDescription("Buy the new game");
                item2.setStatus("normal");
                item2.setCompleted(false);

                todoRepository.save(item);
                todoRepository.save(item1);
                todoRepository.save(item2);

            }
        };
    }

}
