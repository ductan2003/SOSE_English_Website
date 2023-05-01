package com.elearningweb.library.service;

import com.elearningweb.library.model.Admin;
import com.elearningweb.library.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    PasswordEncoder passwordEncoder();
    void save(User user);
    Admin getUser(String username);
    List<User> getAllUsers();
}
