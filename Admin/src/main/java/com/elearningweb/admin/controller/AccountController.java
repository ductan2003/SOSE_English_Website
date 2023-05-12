package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Response;
import com.elearningweb.library.model.User;
import com.elearningweb.library.service.FileService;
import com.elearningweb.library.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;

import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;
    @Value("${project.image}")
    private String path;
    private Response response;
    private  Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/all")
    public List<UserDto> getUserList() {
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public UserDto getByUserName(@PathVariable("username") String username) {
        return userService.getUser(username);
    }

    @PutMapping("/update/userId={userId}")
    public ResponseEntity<UserDto> updateUser(@RequestPart String firstName,
                                              @RequestPart String lastName,
                                              @RequestPart MultipartFile image,
                                              @PathVariable("userId") Long userId) throws Exception{
        User user = userService.getUserById(userId).get();
        if(user == null) new ResponseEntity<>("Username not found!", HttpStatus.BAD_REQUEST);
        String password = user.getPassword();
        String username = user.getUsername();
        String fileName = fileService.updateFile(path, image);
        UserDto updateUser = userService.updateUser(user, username, firstName, lastName, fileName, password);
        LOGGER.info("User {} has Been Updated", username);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
