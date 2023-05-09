package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public UserDto getByUserName(@PathVariable String username) {
        return userService.getUser(username);
    }

    @DeleteMapping("/delete/id={id}")
    public void deleteUserById(@PathVariable("id") long id) {
        userService.deleteById(id);
    }
}
