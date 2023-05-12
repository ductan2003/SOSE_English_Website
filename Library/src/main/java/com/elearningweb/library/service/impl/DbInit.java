package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.Post;
import com.elearningweb.library.model.Role;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.PostRepository;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.FileService;
import com.elearningweb.library.service.PostService;
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
    @Autowired
    PostRepository postRepository;

    List<Role> roles = new ArrayList<>();

    @Override
    public void afterPropertiesSet() throws Exception {

        if (roleRepository.findByName("ROLE_ADMIN") == null
                && roleRepository.findByName("ROLE_USER") == null) {
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
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
        postRepository.save(new Post("aa", "aaa", "aaa", "C:\\Study\\se\\static\\fileImage\\2.jpg"));
        fileService.init();
    }
}
