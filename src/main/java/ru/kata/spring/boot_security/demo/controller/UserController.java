package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<User> userPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User usr = userService.findByUsername(userDetails.getUsername());
//        model.addAttribute("user", usr);
        return new ResponseEntity<>(usr, HttpStatus.OK);
    }
//    @GetMapping
//    public String userPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.findByUsername(userDetails.getUsername());
//        model.addAttribute("user", user);
//        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority());
//        return "UserPage";
//    }

    @GetMapping("/")
    public String logoutPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        return "LoggingPage";
    }
}
