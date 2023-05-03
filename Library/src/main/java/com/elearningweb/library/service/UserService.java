package com.elearningweb.library.service;

import com.elearningweb.library.model.Admin;
import com.elearningweb.library.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    PasswordEncoder passwordEncoder();
    void save(User user);
    Admin getUser(String username);
    List<User> getAllUsers();
    void updateResetPasswordToken(String token, String username) throws Exception;
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);
}
