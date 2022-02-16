package com.ivodrag.springbootusers.controllers;

import com.ivodrag.springbootusers.entity.User;
import com.ivodrag.springbootusers.exception.UserNotFoundException;
import com.ivodrag.springbootusers.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// DTO

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> listUsers = service.findAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Add New User");
        model.addAttribute("subtitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveNewUser(User user, RedirectAttributes ra) {
        service.saveUser(user);
        ra.addFlashAttribute("message", "The User has been successfully saved!");
        return "redirect:/users";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model, RedirectAttributes ra) {
        try {
            User user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("title", "Edit User");
            model.addAttribute("subtitle", "Edit user with ID (" + id + ")");
            return "user_form";
        }catch(UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id,RedirectAttributes ra) {
        try {
            service.deleteUser(id);
            ra.addFlashAttribute("message", "The User with ID(" + id + ")" + " was successfully deleted!");
        }catch(UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }
}
