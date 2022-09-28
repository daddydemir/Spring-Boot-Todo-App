package com.example.todomvc.controller;

import com.example.todomvc.entity.TodoItem;
import com.example.todomvc.entity.User;
import com.example.todomvc.services.TodoItemService;
import com.example.todomvc.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final TodoItemService todoItemService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable int id){
        return ResponseEntity.ok(userService.getById(id));
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<?> getByIdForDelete(@PathVariable int id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping("/user/todo/getById")
    public ResponseEntity<?> addTodoItem(@RequestParam int id, @RequestBody TodoItem todoItem){
        todoItem.setUserId(id);
        todoItemService.saveTodo(todoItem);
        return ResponseEntity.ok("task added.");
    }

    @DeleteMapping("/user/{userId}/todo/{todoId}")
    public ResponseEntity<?> deleteTodo(@PathVariable int userId, @PathVariable int todoId){
        todoItemService.delete(todoId);
        return ResponseEntity.ok("deleted");
    }

}
