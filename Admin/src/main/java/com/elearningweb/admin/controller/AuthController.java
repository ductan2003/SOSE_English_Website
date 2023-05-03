package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.LoginDto;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
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
        if(userRepository.findByUsername(userDto.getUsername()) != null){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
            userService.save(userDto);
            return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
    }
}
