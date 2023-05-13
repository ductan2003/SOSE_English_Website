package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto getUser(String username);

    User getUserByUsername(String username);

    List<UserDto> getAllUsers();

    UserDto save(UserDto userDto);

    boolean checkUserName(String username);

    UserDto updateUser(User user, String username, String firstName, String lastName, String password);

    UserDto changePassword(User user, String newPassword);

    UserDto updateProfileImage(User user, String image);
}

