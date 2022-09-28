package com.example.todomvc;

import io.swagger.annotations.Api;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Api(value = "Spring Boot ToDo Application")
@SpringBootApplication
public class TodoMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoMvcApplication.class, args);
    }

}
