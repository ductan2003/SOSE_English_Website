package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
    UserDto save(UserDto userDto);
    void deleteById(long id);
    boolean checkUserName(String username);
    UserDto updateUser(User user, String username, String firstName, String lastName, String fileName, String password);
    User getUserById(long userId);

    UserDto changePassword(User user, String newPassword);
}

