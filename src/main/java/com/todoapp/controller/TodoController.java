package com.todoapp.controller;

import com.todoapp.model.TodoItem;
import com.todoapp.model.User;
import com.todoapp.repository.TodoRepository;
import com.todoapp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<TodoItem> getTodos(Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return todoRepository.findByUser(user);
    }

    @PostMapping
    public TodoItem addTodo(@RequestBody TodoItem todo, Authentication auth) {
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        todo.setUser(user);
        return todoRepository.save(todo);
    }
}
