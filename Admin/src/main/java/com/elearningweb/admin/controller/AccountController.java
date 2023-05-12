package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.User;
import com.elearningweb.library.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<?> getUserList() {
        List<UserDto> users = userService.getAllUsers();
        Map<Object, Object> map = Map.of("total", users.size(), "userList", users);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{username}")
    public UserDto getByUserName(@PathVariable("username") String username) {
        return userService.getUser(username);
    }
}
