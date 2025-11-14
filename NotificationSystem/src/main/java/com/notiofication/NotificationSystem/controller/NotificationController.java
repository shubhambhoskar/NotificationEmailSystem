package com.notiofication.NotificationSystem.controller;

import com.notiofication.NotificationSystem.Repository.UserRepository;
import com.notiofication.NotificationSystem.Service.NotificationProducer;
import com.notiofication.NotificationSystem.model.Notification;
import com.notiofication.NotificationSystem.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationProducer producer;
    private final UserRepository userRepository;

    public NotificationController(NotificationProducer producer, UserRepository userRepository) {
        this.producer = producer;
        this.userRepository = userRepository;
    }

    // To send notification to all users
    @PostMapping("/send")
    public String sendToAllActiveUsers() {
        List<User> activeUsers = userRepository.findAll();

        for (User user : activeUsers) {
            Notification notification = new Notification(
                    user.getEmail(),
                    "Welcome to Notification System!",
                    "Hello " + user.getName() + ", this is a Kafka-based test mail."
            );
            producer.sendNotification(notification);
        }

        return "✅ Notifications queued for " + activeUsers.size() + " users!";
    }
}
