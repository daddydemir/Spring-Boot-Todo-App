package com.example.todomvc.services;

import com.example.todomvc.entity.TodoItem;
import com.example.todomvc.repository.TodoItemRepository;
import com.example.todomvc.temp.CurrentTodoItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@AllArgsConstructor
public class TodoItemServiceImpl implements TodoItemService{

    private final TodoItemRepository todoItemRepository;
    private final UserService userService;

    @Transactional
    @Override
    public Collection<TodoItem> getTodosForLoggedUser() {
        return todoItemRepository.findAllByUserId(userService.getLoggedUserId());
    }

    @Override
    public void completeTodo(int id) {
        TodoItem todoItem = todoItemRepository.getReferenceById(id);
        todoItem.setCompleted(!todoItem.isCompleted());

        todoItemRepository.save(todoItem);
    }

    @Override
    public void delete(int id) {
        todoItemRepository.deleteById(id);
    }

    @Override
    public void saveTodo(TodoItem todoItem) {
        todoItemRepository.save(todoItem);
    }

    @Override
    public CurrentTodoItem itemToCurrentTodoItem(int id) {
//        CurrentTodoItem item =
        return null;
    }
}
