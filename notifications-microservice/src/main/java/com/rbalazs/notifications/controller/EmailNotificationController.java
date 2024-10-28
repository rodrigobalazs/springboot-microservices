package com.rbalazs.notifications.controller;

import com.rbalazs.notifications.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Email Notifications REST Controller.
 *
 * @author Rodrigo Balazs
 */
@RestController
@RequestMapping("/emailNotifications")
public class EmailNotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationController.class);
    private final EmailNotificationService emailNotificationService;

    @Autowired
    public EmailNotificationController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/send-new-order-notification")
    public void sendNewOrderNotification(@RequestParam(value = "customerEmail") String customerEmail,
            @RequestParam(value = "orderId") Long orderId) {
        LOGGER.info("starts to execute emailNotificationController.sendNewOrderNotification() for customer email:{}" ,
            customerEmail);
        emailNotificationService.sendNewOrderNotification(customerEmail, orderId);
    }
}

