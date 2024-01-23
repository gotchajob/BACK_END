package com.example.gj.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendEmailResetPassword(String email, String code, String fullName) {
        String subject = " Password Reset Request for GotchaJob.com";
        String body = "Dear "+ fullName +",\n" +
                "\n" +
                "I hope this email finds you well. It appears that you have requested a password reset for your account. To ensure the security of your account, we have initiated the password reset process.\n" +
                "\n" +
                "Please use the following code to reset your password:\n" +
                "\n" +
                "Reset Code: " + code + "\n" +
                "\n" +
                "If you did not initiate this password reset request, please disregard this email. Your account security is important to us, and we recommend changing your password periodically to enhance security.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "GotchaJob\n";

        sendEmail(email, subject, body);
    }

    public void sendEmailVerifyEmail(String email, String code, String fullName) {
        String subject = "Account Verification Code for GotchaJob.com";
        String body = "Dear " + fullName + ",\n" +
                "\n" +
                "Thank you for registering with our service. To complete your registration, please use the following verification code:\n" +
                code +
                "\n" +
                "Enter this code on our website to verify your email address and activate your account.\n" +
                "\n" +
                "If you didn't sign up for our service, please ignore this email.\n" +
                "\n" +
                "Best regards,\n" +
                "GotchaJob";

        sendEmail(email, subject, body);
    }
}
