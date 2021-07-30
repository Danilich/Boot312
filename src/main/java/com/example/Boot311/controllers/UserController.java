package com.example.Boot311.controllers;


import com.example.Boot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.Boot311.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;


    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "index";
        }
        return "redirect:/user/forbidden";
    }
}
