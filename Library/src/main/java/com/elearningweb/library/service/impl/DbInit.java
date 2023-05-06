package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
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
    FileServiceImpl fileService;

    List<Role> roles = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {

        if (roleRepository.findByName("ADMIN") == null
                && roleRepository.findByName("USER") == null) {
            roles.add(new Role("ADMIN"));
            roles.add(new Role("USER"));
            roleRepository.saveAll(roles);
        }
        if (userRepository.findByUserName("admin") == null) {
            User admin = new User();
            admin.setFirstName("He");
            admin.setLastName("He");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
        fileService.init();
    }
}
