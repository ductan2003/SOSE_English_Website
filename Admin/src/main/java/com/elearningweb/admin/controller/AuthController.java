package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.impl.EmailServiceImpl;
import com.elearningweb.library.service.impl.PasswordResetServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import com.google.api.Http;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    @EqualsAndHashCode.Exclude private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordResetServiceImpl passwordResetService;

    @Autowired
    private EmailServiceImpl emailService;


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

    @GetMapping("/check-username/{username}")
    public ResponseEntity<User> checkUserName(@PathVariable("username") String username) {
        boolean usernameExists = userService.checkUserName(username);
        if(usernameExists) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/forgot-password")
//    @RequestMapping(value = "/forgot-password", method = RequestMethod.GET)
    @RequestMapping(value = "/forgot-password", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<Map<String, String>> forgotPassword(@RequestPart String username) throws MessagingException {
        passwordResetService.sendPasswordResetEmail(username);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Password change request sent successfully.");
        return ResponseEntity.ok(responseBody);
    }

    //Yêu cầu đặt lại mật khẩu dựa trên mã token thông báo được gửi qua email (username). Mail xác nhận có chứa button để xác nhận
    @PostMapping("/reset/{token}")
    public ResponseEntity<Map<String, String>> resetPassword(@PathVariable String token, @RequestPart String username, @RequestPart String password) throws MessagingException {
        try {
            passwordResetService.changePassword(token, password, username);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Password changed successfully.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MessagingException e){
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
