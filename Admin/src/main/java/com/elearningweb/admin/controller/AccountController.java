package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<UserDto> getUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDto getByUserName(@PathVariable("username") String username) {
        return userService.getUser(username);
    }
}
