package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.elearningweb.library.model.Admin;
import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.impl.AdminServiceImpl;
import com.elearningweb.library.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController //@Controller
//@RequestMapping("/api")
@CrossOrigin("http://localhost:8080/") //Cái này của VueJS
//@CrossOrigin
@RequestMapping(
        method = {RequestMethod.POST, RequestMethod.GET}
)
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

//    @Autowired
    private TokenStore tokenStore;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        System.out.println("hihi");
        return "login";
    }

    @GetMapping("/logout")
    public void logoutForm(@RequestParam("access_token") String accessToken) {
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }

    @RequestMapping("/index")
    public String home(Model model) {
        model.addAttribute("title", "Home Page");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/login";
        }
        return "index";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }
    @GetMapping("/users")
    public List<User> users(){
        return userService.getAllUsers();
    }


    @PostMapping("/register")
    public String register(@RequestParam UserDto registerDto, Model model) {
        if(!registerDto.getPassword().equals(registerDto.getPasswordConfirmation()))
            return "Error the two passwords do not match";
        else if(registerDto.getUsername() != null)
            return "Error this username already exists";
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        if(pattern.matcher(registerDto.getUsername()).find())
            return "No special characters are allowed in the username";

        userService.save(new User(registerDto.getFirstName(), registerDto.getLastName(), registerDto.getUsername(), registerDto.getPassword(), Arrays.asList(new Role("USER"), new Role("ACTUATOR"))));
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }


//    @GetMapping("/register")
//    public String register(Model model) {
//        model.addAttribute("title", "Register");
//        model.addAttribute("adminDto", new AdminDto());
//        return "register";
//    }
//


//    @PostMapping("/register-new")
//    public String addNewUser(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
//                             BindingResult result,
//                             Model model,
////                              RedirectAttributes redirectAttributes,
//                             HttpSession session) {
//
//        try {
//            session.removeAttribute("message");
//            if (result.hasErrors()) {
//                model.addAttribute("adminDto", adminDto);
//                result.toString();
//                return "register";
//            }
//            String username = adminDto.getUsername();
//            Admin admin = adminService.findByUsername(username);
//            if (admin != null) {
//                model.addAttribute("adminDto", adminDto);
//                System.out.println("user not null");
//                model.addAttribute("emailError", "Your email has been registered!");
//                session.setAttribute("message", "Your email has been registered!");
//                return "register";
//            }
//            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
//                System.out.println("success");
//                session.setAttribute("message", "Register successfully!");
//                model.addAttribute("success", "Register successfully!");
//                model.addAttribute("adminDto", adminDto);
//                adminService.save(adminDto);
//            } else {
//                model.addAttribute("adminDto", adminDto);
//                System.out.println("Password not same!");
//                model.addAttribute("passwordError", "Your password maybe wrong! Check again :>");
//                session.setAttribute("message", "Password not same!");
//                return "register";
//            }
//
////            userService.save(userDto);
////            model.addAttribute("userDto", userDto);
////            redirectAttributes.addFlashAttribute("message", "Register sucessfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.setAttribute("message", "The server has been wrong! Try again late!");
//            model.addAttribute("errors", "The server has been wrong!");
//        }
//        return "register";
//
//    }

}
