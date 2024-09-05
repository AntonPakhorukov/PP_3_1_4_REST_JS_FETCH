package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(Principal principal, Model model) {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//    @GetMapping
//    public String listUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        model.addAttribute("users", userService.findAll());
//        model.addAttribute("auth", userDetails.getAuthorities());
//        User user = userService.findByUsername(userDetails.getUsername());
//        model.addAttribute("user", user);
//        model.addAttribute("roles", roleService.getAllRoles());
//        return "UsersPage";
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public String GetUserById(@PathVariable("id") long id, Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority());
//        model.addAttribute("user", userService.findById(id));
//        return "UserPage";
//    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("roles", roleService.getAllRoles());
        User currentUser = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("user", currentUser);
        return "createUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, @RequestParam("role") List<String> role) {
        userService.saveUser(user, role);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}

