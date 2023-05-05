package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.UserService;
import com.elearningweb.library.converter.Converter;
import com.elearningweb.library.dto.UserDto;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.RoleRepository;
import com.elearningweb.library.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    private static long EXPIRE_TOKEN_AFTER_MINUTES = 30;

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
    public String forgotPassword(String username) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserName(username));
        if(!userOptional.isPresent()){
            return "Invalid username";
        }
        User user = userOptional.get();
        user.setResetPasswordToken(generateToken());
        user.setTokenCreationDate(LocalDateTime.now());
        user = userRepository.save(user);
        return user.getResetPasswordToken();
    }

    @Override
    public String updatePassword(String token, String password) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByResetPasswordToken(token));
        if(!userOptional.isPresent()) {
            return "Invalid token";
        }
        User user = userOptional.get();
        LocalDateTime tokenCreationDate = user.getTokenCreationDate();
        if(isTokenExpired(tokenCreationDate)) {
            return "Token expried";
        }
        user.setPassword(password);
        user.setResetPasswordToken(null);
        user.setTokenCreationDate(null);
        userRepository.save(user);
        return "Your password sucessfully updated!";
    }
    @Override
    public String generateToken() {
        StringBuilder token = new StringBuilder();
        return token.append(UUID.randomUUID().toString())
                .append(UUID.randomUUID().toString()).toString();
    }

    @Override
    public boolean isTokenExpired(LocalDateTime tokenCreationDate) {
        LocalDateTime now = LocalDateTime.now();
        Duration diff = Duration.between(tokenCreationDate, now);
        return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
    }


    @Override
    public UserDto getUser(String username) {
        return converter.userToDto(userRepository.findByUserName(username));
    }


    @Override
    public List<UserDto> getAllUsers() {
        return converter.listUserToDto(userRepository.findAll());
    }

}
