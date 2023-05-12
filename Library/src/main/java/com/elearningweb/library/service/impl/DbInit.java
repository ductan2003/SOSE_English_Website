package com.elearningweb.library.service.impl;

import com.elearningweb.library.dto.CategoryDto;
import com.elearningweb.library.dto.ExamDto;
import com.elearningweb.library.dto.QuestionDto;
import com.elearningweb.library.model.Question;
import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.util.ExcelReadingUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbInit implements InitializingBean {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    ExamServiceImpl examService;
    @Autowired
    ExcelReadingUtil excelReadingUtil;
    @Autowired
    FileServiceImpl fileService;

    List<Role> roles = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        //CREATE ROLE
        if (roleRepository.findByName("ROLE_ADMIN") == null
                && roleRepository.findByName("ROLE_USER") == null) {
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
            roleRepository.saveAll(roles);
        }
        //CREATE USERS TEST

        String password = passwordEncoder.encode("1234");
        if (userRepository.findByUserName("admin") == null) {
            userRepository.save(new User("He",
                    "He",
                    "admin",
                    password,
                    roles));
        }
        if (userRepository.findByUserName("user1") == null) {
            userRepository.save(new User("Tan Duc",
                    "Nguyen",
                    "user1",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user2") == null) {
            userRepository.save(new User("Ha",
                    "Nguyen",
                    "user2",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user3") == null) {
            userRepository.save(new User("Duong",
                    "Nguyen",
                    "user3",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user4") == null) {
            userRepository.save(new User("Bach",
                    "Khi",
                    "user4",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }
        if (userRepository.findByUserName("user5") == null) {
            userRepository.save(new User("Hieu",
                    "Pham",
                    "user5",
                    password,
                    List.of(roleRepository.findByName("ROLE_USER"))));
        }

        //INIT FILE FOLDER
        fileService.init();

        //CREATE TEST EXAMS
        examService.save(new ExamDto(
                "READING TEST 1",
                "A+ CNPM",
                "2023",
                "C:\\Users\\fpt\\se\\static\\fileQuestion\\test1.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 2",
                "B+ CNPM",
                "2022",
                "C:\\Users\\fpt\\se\\static\\fileQuestion\\test2.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 3",
                "A+ CNPM",
                "2022",
                "C:\\Users\\fpt\\se\\static\\fileQuestion\\test3.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 4",
                "C+ CNPM",
                "2023",
                "C:\\Users\\fpt\\se\\static\\fileQuestion\\test4.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
        examService.save(new ExamDto(
                "READING TEST 5",
                "D+ CNPM",
                "2023",
                "C:\\Users\\fpt\\se\\static\\fileQuestion\\test5.xlsx",
                "48c73d1f-ba97-4ee4-8539-6642440273eb.jpg",
                new CategoryDto(null, "Reading")));
    }
}
