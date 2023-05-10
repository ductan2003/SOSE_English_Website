package com.elearningweb.library.service;

import javax.mail.MessagingException;

public interface EmailService {
    String sendPasswordResetEmail(String recipient, String token) throws MessagingException;
}
