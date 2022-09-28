package com.example.todomvc.services;

import com.example.todomvc.entity.User;
import com.example.todomvc.temp.CurrentUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    public User findUserByEmail(String email);

    public void saveUser(CurrentUser currentUser);

    public int getLoggedUserId();

    public User getLoggedUser();

    public List<User> getAll();

    public Optional<User> getById(int id);

    public String deleteUser(int id);
}
