package com.notiofication.NotificationSystem.controller;

import com.notiofication.NotificationSystem.Repository.UserRepository;
import com.notiofication.NotificationSystem.Service.NotificationProducer;
import com.notiofication.NotificationSystem.Service.NotificationService;
import com.notiofication.NotificationSystem.model.Notification;
import com.notiofication.NotificationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public String sendToAllActiveUsers() {
        return notificationService.sendToAllActiveUsers();
    }
}
