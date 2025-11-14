package com.notiofication.NotificationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailTestController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/sendTestEmail")
    public String sendTestEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("shubhambhoskar@gmail.com");
            message.setSubject("Test Email from Spring Boot");
            message.setText("Hello, this is a test email from your Notification System!");
            mailSender.send(message);
            return "✅ Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed: " + e.getMessage();
        }
    }
}
