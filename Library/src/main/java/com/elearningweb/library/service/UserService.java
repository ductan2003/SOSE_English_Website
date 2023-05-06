package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
    UserDto save(UserDto userDto);
    boolean checkUserName(String username);
}
