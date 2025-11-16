package com.notiofication.NotificationSystem.Service;

import com.notiofication.NotificationSystem.Repository.UserRepository;
import com.notiofication.NotificationSystem.model.Notification;
import com.notiofication.NotificationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationProducer producer;

    public String sendToAllActiveUsers() {
        try {
            List<User> activeUsers = userRepository.findAll();

            if (activeUsers.isEmpty()) {
                return "No active users found!";
            }

            ExecutorService executorService = Executors.newFixedThreadPool(5);

            for (User user : activeUsers) {
                executorService.submit(() -> {

                    try {
                        Notification notification = new Notification(
                                user.getEmail(),
                                "Welcome to Notification System!",
                                "Hello " + user.getName() + ", this is a Kafka-based test mail."
                        );

                        producer.sendNotification(notification);

                    } catch (Exception e) {
                        System.err.println("Failed to send notification to: " + user.getEmail());
                        e.printStackTrace();
                    }
                });
            }

            executorService.shutdown();

            return "Notifications queued for " + activeUsers.size() + " users!";

        } catch (Exception ex) {
            System.err.println("Error occurred while sending notifications: " + ex.getMessage());
            ex.printStackTrace();

            return "Failed to queue notifications due to an internal error!";
        }
    }

}
