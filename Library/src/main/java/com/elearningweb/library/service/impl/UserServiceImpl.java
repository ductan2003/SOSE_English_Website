package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.Admin;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Admin getUser(String username) {
        return (Admin) userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    public void updateResetPasswordToken(String token, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new Exception("Could not find any use" + username);
        }
    }
    public User getByResetPasswordToken(String token){
        return userRepository.findByResetPasswordToken(token);
    }
    public void updatePassword(User user, String newPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
