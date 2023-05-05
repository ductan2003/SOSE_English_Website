package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;


import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
    UserDto save(UserDto userDto);
    String forgotPassword(String username);
    String updatePassword(String token, String password);
    String generateToken();
    boolean isTokenExpired(LocalDateTime tokenCreationDate);
}
