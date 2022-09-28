package com.example.todomvc.dao;

import com.example.todomvc.entity.TodoItem;

import java.util.Collection;

public interface TodoItemDao {

    public Collection<TodoItem> getTodosForLoggedUserById(int userId);

    public void completeTodo(TodoItem item);

    public void deleteTodo(TodoItem item);
}
