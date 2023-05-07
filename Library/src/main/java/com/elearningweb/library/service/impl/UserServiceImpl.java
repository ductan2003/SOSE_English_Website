package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.UserService;
import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    Converter converter;

    @Override
    public UserDto save(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(roleRepository.findByName("USER")));

        userRepository.save(user);
        return userDto;
    }

    @Override
    public UserDto getUser(String username) {
        return converter.userToDto(userRepository.findByUserName(username));
    }


    @Override
    public List<UserDto> getAllUsers() {
        return converter.listUserToDto(userRepository.findAll());
    }

    @Override
    public boolean checkUserName(String username) {
        return userRepository.findByUserName(username) != null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }

}
