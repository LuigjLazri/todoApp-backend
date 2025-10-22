package com.todoapp.repository;

import com.todoapp.model.TodoItem;
import com.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
    List<TodoItem> findByUser(User user);
}
