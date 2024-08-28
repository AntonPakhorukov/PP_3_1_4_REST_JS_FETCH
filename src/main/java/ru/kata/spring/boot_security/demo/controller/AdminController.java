package ru.kata.spring.boot_security.demo.controller;

//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String listUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @GetMapping("/{id}")
    public String GetUserById(@PathVariable("id") long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority());
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("cond", true);
        return "UserPage";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "createUser";
    }

    @PostMapping
    public String createUser(@ModelAttribute("user") /*@Valid*/ User user, BindingResult bindingResult,
                             @RequestParam Long roleId) {
        if (bindingResult.hasErrors()) {
            return "createUser";
        }
        Role role = roleService.findById(roleId);
        user.getRoles().add(role);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "updateUser";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@ModelAttribute("user") /*@Valid*/ User user, BindingResult bindingResult,
                             @PathVariable("id") long id) {
        if (bindingResult.hasErrors()) {
            return "updateUser";
        }
        System.out.println("Put_update");
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

