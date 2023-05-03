package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
    UserDto save(UserDto userDto);
}
