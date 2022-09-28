package com.example.todomvc.dao;

import com.example.todomvc.entity.User;

public interface UserDao {

    public User findUserByEmail(String email);

    public void saveUser(User user);
}
