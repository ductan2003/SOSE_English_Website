package com.elearningweb.library.service.impl;

import com.elearningweb.library.model.PasswordResetToken;
import com.elearningweb.library.model.User;
import com.elearningweb.library.repository.PasswordResetTokenRespository;
import com.elearningweb.library.repository.UserRepository;
import com.elearningweb.library.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRespository passwordResetTokenRespository;

    @Autowired
    private EmailServiceImpl emailService;
    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setUser(user);
        resetToken.setToken(token);
        passwordResetTokenRespository.save(resetToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token) {
        return passwordResetTokenRespository.findByToken(token);
    }

    @Override
    public void sendPasswordResetEmail(String username) throws MessagingException {
        User user = userRepository.findByUserName(username);
        if(user == null || user.getUsername() == null) {
            return;
        }
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user, token);
        emailService.sendPasswordResetEmail(user.getUsername(), token);
    }

    @Override
    public void changePassword(String token, String newPassword, String usernameRequest) throws MessagingException {
        PasswordResetToken resetToken = passwordResetTokenRespository.findByToken(token);
        if(resetToken == null) {
            throw new MessagingException("Invalid or expired token!");
        }
        User user = resetToken.getUser();
        if(user == null || !user.getUsername().equals(usernameRequest)) {
            throw new MessagingException("User not found or invalid username!");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        passwordResetTokenRespository.delete(resetToken);
    }
}
