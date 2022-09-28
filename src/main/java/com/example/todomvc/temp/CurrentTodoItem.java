package com.example.todomvc.temp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentTodoItem {

    private int id;
    private int userId;
    private String title;
    private boolean completed;
}
