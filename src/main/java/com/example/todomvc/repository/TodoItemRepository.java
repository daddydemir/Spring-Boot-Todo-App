package com.example.todomvc.repository;

import com.example.todomvc.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {

    Collection<TodoItem> findAllByUserId(int userId);
}
