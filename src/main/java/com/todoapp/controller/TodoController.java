package com.todoapp.controller;

import com.todoapp.model.TodoItem;
import com.todoapp.model.User;
import com.todoapp.repository.TodoRepository;
import com.todoapp.repository.UserRepository;
import org.springframework.http.ResponseEntity;
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
        System.out.println("GET TODOS");
        User user = userRepository.findByUsername(auth.getName()).orElseThrow();
        return todoRepository.findByUser(user);
    }

    @PostMapping
    public TodoItem create(@RequestBody TodoItem todoItem, Authentication auth) {
        var user = userRepository.findByUsername(auth.getName()).orElseThrow();
        var ti1 = new TodoItem();
        ti1.setTitle(todoItem.getTitle());
        ti1.setDescription(todoItem.getDescription());
        ti1.setStatus(todoItem.getStatus());
        ti1.setCompleted(false);
        ti1.setUser(user);
        return todoRepository.save(ti1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, Authentication auth) {
        var user = userRepository.findByUsername(auth.getName()).orElseThrow();
        System.out.println("DELETE ACTION:");
        System.out.println("USER ID: " + user.getId());
        var todo = todoRepository.findById(id)
                .filter(t -> t.getUser().getId().equals(user.getId()))
                .orElseThrow();

        todoRepository.delete(todo);
        return ResponseEntity.noContent().build();
    }
}
