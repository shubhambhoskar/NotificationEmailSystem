package com.notiofication.NotificationSystem.Service;

import com.notiofication.NotificationSystem.model.Notification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, Notification> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, Notification> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(Notification notification) {
        kafkaTemplate.send("email_notifications", notification);
        System.out.println("📤 Notification sent to Kafka: " + notification.getRecipient());
    }
}
