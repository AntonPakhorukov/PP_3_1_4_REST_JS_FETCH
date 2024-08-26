package ru.kata.spring.boot_security.demo.service;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService {
    User findById(Long id);
    List<User> findAll();
    User saveUser(User user);
    void updateUser(User user);
    void deleteById(Long id);
}
