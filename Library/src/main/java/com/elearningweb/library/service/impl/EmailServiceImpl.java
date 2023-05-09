package com.elearningweb.library.service.impl;

import com.elearningweb.library.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String sendPasswordResetEmail(String recipient, String token) {
        try {
            javax.mail.internet.MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("psjily@gmail.com");
            mimeMessageHelper.setTo(recipient);
            mimeMessageHelper.setSubject("Reset Password - SOSE");
            String text = "<html>"
                    +
                    "<body style='font-family: Arial, sans-serif;'>"
                    +
                    "<h1>Reset password</h1>"
                    +
                    "<p>Hello hehe</p>"
                    +
                    "<p>You requested to reset the password of the email registered in our system. Click on the link below to proceed:</p>"
                    +
                    "<p style='background-color: #24a0ed; padding: 10px; color: white; border-radius: 5px; display: inline-block;'><a href='https://front-end/"
                    + recipient
                    + token + "' style='color: white; text-decoration: none;'>Redefine password</p>"
                    +
                    "<p>Thank you for your participation and attention in testing the project. If you have any questions, feel free to contact us.</p>"
                    +
                    "<p>Yours sincerely,</p>"
                    +
                    "<p>SOSE.</p>"
                    +
                    "<span style='background-color: #0e76a8 ; padding: 10px; color: white; border-radius: 5px; display: inline-block;'><a href='https://www.linkedin.com/in/habyhongg/' style='color: white; text-decoration: none;'>LinkedIn</a></span>  "
                    +
                    "<span style='background-color: #171515; padding: 10px; color: white; border-radius: 5px; display: inline-block;'><a href='https://github.com/ductan2003/se' style='color: white; text-decoration: none;'>GitHub</a></span>"

                    +
                    "</body>" +
                    "</html>";
            mimeMessageHelper.setText(text, true);
            javaMailSender.send(mimeMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail" + e.getMessage();
        }
    }
}