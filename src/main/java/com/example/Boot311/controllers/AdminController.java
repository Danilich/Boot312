package com.example.Boot311.controllers;


import com.example.Boot311.models.User;
import com.example.Boot311.service.RoleService;
import com.example.Boot311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    public UserService userService;

    @Autowired
    public RoleService roleService;


    @GetMapping("/users")
    public String index(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.listUsers());
        return "index";
    }


    @PostMapping()
    public String create(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roleIds) {
        user.setRoles(Arrays.stream(roleIds).map(id -> roleService.getRoleById(id)).collect(Collectors.toSet()));
        userService.add(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "fragments/modals/edit_modal";
    }

    @PatchMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("role_select") Long[] roleIds, @AuthenticationPrincipal User authUser) {
        user.setRoles(Arrays.stream(roleIds).map(id -> roleService.getRoleById(id)).collect(Collectors.toSet()));
        if (user.getUsername().equals(authUser.getUsername())) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        userService.edit(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}/delete")
    public String delete_page(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "fragments/modals/delete_modal";
    }

    @DeleteMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
