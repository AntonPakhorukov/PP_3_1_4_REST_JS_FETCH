package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    User saveUser(User user, List<String> roles);
    void deleteById(Long id);
    User findByUsername(String username);
}
