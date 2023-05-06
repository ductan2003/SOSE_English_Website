package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.impl.UserServiceImpl;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.lang.String;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    @EqualsAndHashCode.Exclude private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;


    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestPart String username,
                                                   @RequestPart String password){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestPart String firstName,
                                          @RequestPart String lastName,
                                          @RequestPart String username,
                                          @RequestPart String password,
                                          @RequestPart String confirmPassword){
        UserDto userDto = new UserDto(firstName, lastName, username, password, confirmPassword);
        // add check for username exists in a DB
        if(userRepository.findByUserName(userDto.getUsername()) != null){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
            userService.save(userDto);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String username) {
        String response = userService.forgotPassword(username);
        if(!response.startsWith("Invalid")){
            response = "http://localhost:8080/reset-password?token=" + response;
        }
        return response;
    }

    @PutMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password) {
        return userService.updatePassword(token, password);
    }
}
