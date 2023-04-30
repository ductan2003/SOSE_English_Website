package com.elearningweb.admin.controller;

import com.elearningweb.library.dto.AdminDto;
import com.elearningweb.library.model.Admin;
import com.elearningweb.library.service.impl.AdminServiceImpl;
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

@Controller //@Controller
//@RequestMapping("/api")
@CrossOrigin("http://localhost:8080/") //Cái này của VueJS
//@CrossOrigin
@RequestMapping(
        method = {RequestMethod.POST, RequestMethod.GET}
)
public class LoginController {
    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("title", "Login");
        System.out.println("hihi");
        return "login";
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

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

//    @PostMapping("/save")
//    public String saveUser(@RequestBody UserDto userDto){
//        String id = String.valueOf(userService.save(userDto));
//        return id;
//    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";
    }


    @PostMapping("/register-new")
    public String addNewUser(@Valid @ModelAttribute("adminDto") AdminDto adminDto,
                             BindingResult result,
                             Model model,
//                              RedirectAttributes redirectAttributes,
                             HttpSession session) {

        try {
            session.removeAttribute("message");
            if (result.hasErrors()) {
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin != null) {
                model.addAttribute("adminDto", adminDto);
                System.out.println("user not null");
                model.addAttribute("emailError", "Your email has been registered!");
                session.setAttribute("message", "Your email has been registered!");
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())) {
                System.out.println("success");
                session.setAttribute("message", "Register successfully!");
                model.addAttribute("success", "Register successfully!");
                model.addAttribute("adminDto", adminDto);
                adminService.save(adminDto);
            } else {
                model.addAttribute("adminDto", adminDto);
                System.out.println("Password not same!");
                model.addAttribute("passwordError", "Your password maybe wrong! Check again :>");
                session.setAttribute("message", "Password not same!");
                return "register";
            }

//            userService.save(userDto);
//            model.addAttribute("userDto", userDto);
//            redirectAttributes.addFlashAttribute("message", "Register sucessfully!");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", "The server has been wrong! Try again late!");
            model.addAttribute("errors", "The server has been wrong!");
        }
        return "register";

    }

}
