package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
    public String userPage(Model model, @AuthenticationPrincipal UserDetails userDetails, Principal principal) {
        User user = userRepository.findByName(userDetails.getUsername());
        model.addAttribute("user", user);
        model.addAttribute("role", userDetails.getAuthorities().iterator().next().getAuthority());
        return "UserPage";
    }

    @GetMapping("/")
    public String logoutPage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        return "LogingPage";
    }
}
