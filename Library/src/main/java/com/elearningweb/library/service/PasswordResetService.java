package com.elearningweb.library.service;

import com.elearningweb.library.model.PasswordResetToken;
import com.elearningweb.library.model.User;

import javax.mail.MessagingException;

public interface PasswordResetService {
    void createPasswordResetTokenForUser(User user, String token);
    PasswordResetToken getPasswordResetToken(String token);
    void sendPasswordResetEmail(String username) throws MessagingException;
    void changePassword(String token, String newPassword, String usernameRequest) throws MessagingException;


}
