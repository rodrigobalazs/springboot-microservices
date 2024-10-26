package com.rbalazs.notifications.controller;

import com.rbalazs.notifications.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Notifications REST Controller.
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/send")
    public void sendNotification(@RequestParam String message) {
        LOGGER.info("starts to execute notificationController.sendNotification() with message:{}" , message);
        notificationService.sendNotification(message);
    }
}

