package com.example.todomvc.services;

import com.example.todomvc.entity.TodoItem;
import com.example.todomvc.temp.CurrentTodoItem;

import java.util.Collection;

public interface TodoItemService {

    public Collection<TodoItem> getTodosForLoggedUser();

    public void completeTodo(int id);

    public void delete(int id);

    public void saveTodo(TodoItem todoItem);

    public CurrentTodoItem itemToCurrentTodoItem(int id);
}
