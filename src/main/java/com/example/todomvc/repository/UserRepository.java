package com.example.todomvc.repository;

import com.example.todomvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {


    User findByEmail(String email);
    
    
}
