package com.example.todomvc.controller;

import com.example.todomvc.entity.TodoItem;
import com.example.todomvc.entity.User;
import com.example.todomvc.services.TodoItemService;
import com.example.todomvc.services.UserService;
import com.example.todomvc.temp.CurrentUser;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

@Controller
@AllArgsConstructor
public class TodoItemController {

    private final TodoItemService todoItemService;
    private  final UserService userService;


    @ApiOperation(value = "ana sayfa" , notes = "Ana sayfa metodu")
    @GetMapping("/")
    public String index(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals("anonymousUser")){
            // oturum açmamış ise login sayfasına yönlendirilecek
            return "redirect:/login";
        }else{
            TodoItem item = new TodoItem();
            model.addAttribute("todo" , item);
            model.addAttribute("list", todoItemService.getTodosForLoggedUser());
            model.addAttribute("username", userService.getLoggedUser());
            return "index";
        }
    }

    @PostMapping("/todo/save")
    public String saveTodo(@ModelAttribute("todo") TodoItem todoItem, BindingResult result, Model model){
        todoItem.setCompleted(false);
        todoItem.setUserId(userService.getLoggedUser().getId());
        todoItemService.saveTodo(todoItem);
        return "redirect:/";
    }

    @GetMapping("/todo/delete")
    public String deleteTodo(@PathParam("id") int id){
        todoItemService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/todo/complete")
    public String completeTodo(@PathParam("id") int id){
        todoItemService.completeTodo(id);
        return "redirect:/";
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @GetMapping("/register")
    public String registerPage(Model model){
        CurrentUser currentUser = new CurrentUser();
        model.addAttribute("user",currentUser);
        return "register";
    }

    @PostMapping("/register/save")
    public String registration(@ModelAttribute("user") CurrentUser currentUser, BindingResult result, Model model){

        User existingUser = userService.findUserByEmail(currentUser.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            // bu eposta zaten kayıtlı

        }
        if(result.hasErrors()){
            model.addAttribute("user", currentUser);
            return "register";
        }
        userService.saveUser(currentUser);

        // eğer başarılı ise login sayfasına gitsin
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request,response,auth);
        }
        return "redirect:/login?logout";
    }
}
