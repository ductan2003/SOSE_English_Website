package com.elearningweb.library.service;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;


import java.util.List;

public interface UserService {
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
    UserDto save(UserDto userDto);

    void updateResetPasswordToken(String token, String username) throws Exception;
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);
}
