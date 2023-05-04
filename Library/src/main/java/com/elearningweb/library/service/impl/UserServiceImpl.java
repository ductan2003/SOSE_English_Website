package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.UserService;
import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    Converter converter;

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(roleRepository.findByName("USER")));

        userRepository.save(user);
        return userDto;
    }

    @Override
    public void updateResetPasswordToken(String token, String username) throws Exception {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            user.setResetPasswordToken(token);
            userRepository.save(user);
        } else {
            throw new Exception("Could not find any use" + username);
        }
    }

    @Override
    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }


    @Override
    public UserDto getUser(String username) {
        return converter.userToDto(userRepository.findByUsername(username));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return converter.listUserToDto(userRepository.findAll());
    }
}
