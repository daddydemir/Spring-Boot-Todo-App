package com.example.todomvc.services;

import com.example.todomvc.entity.User;
import com.example.todomvc.repository.UserRepository;
import com.example.todomvc.temp.CurrentUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@AllArgsConstructor
public class UserServiceImpl implements UserService{


    private final UserRepository userRepository;
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void saveUser(CurrentUser currentUser) {
        User user = new User();
        String parola = passwordEncoder.encode(currentUser.getPassword());
        //user.setPassword(passwordEncoder.encode(currentUser.getPassword()));
        user.setEmail(currentUser.getEmail());
        user.setName(currentUser.getName());
        user.setSurname(currentUser.getSurname());
        user.setPassword(parola);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public int getLoggedUserId() {
        User user = userRepository.findByEmail(loggedUserEmail());
        return user.getId();
    }

    @Override
    public User getLoggedUser() {
        User user = userRepository.findByEmail(loggedUserEmail());
        return user;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(int id) {
        return userRepository.findById(Integer.valueOf(id));
    }

    @Override
    public String deleteUser(int id) {
        userRepository.deleteById(Integer.valueOf(id));
        return "Ok";
    }

    private String loggedUserEmail(){
        Object principal = SecurityContextHolder
                .getContext().
                getAuthentication().
                getPrincipal();
        if (principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrMail) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(usernameOrMail);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
