package com.example.todomvc.temp;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser {

    private int id;
    private String name;
    private String surname;
    private String password;
    //private String matchingPassword;
    private String email;
}
