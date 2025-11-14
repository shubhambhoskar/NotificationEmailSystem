package com.notiofication.NotificationSystem.Service;

import com.notiofication.NotificationSystem.model.Notification;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationConsumer {

    private final JavaMailSender mailSender;

    public EmailNotificationConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // This method runs automatically when Kafka receives a message
    @KafkaListener(topics = "email_notifications", groupId = "email-service-group")
    public void consume(Notification notification) {
        System.out.println("📩 Received message for: " + notification.getRecipient());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(notification.getRecipient());
            message.setSubject(notification.getSubject());
            message.setText(notification.getMessage());

            // ⬇️ THIS IS WHERE THE MAIL IS ACTUALLY SENT
            mailSender.send(message);

            System.out.println("✅ Email sent successfully to: " + notification.getRecipient());
        } catch (Exception e) {
            System.err.println("❌ Error sending email to: " + notification.getRecipient());
            e.printStackTrace();
        }
    }

}
