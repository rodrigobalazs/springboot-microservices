package com.rbalazs.notifications.controller;

import com.rbalazs.notifications.controller.swagger.EmailNotificationControllerSwagger;
import com.rbalazs.notifications.service.EmailNotificationService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Email Notifications REST Controller.
 * API Documentation/Swagger at => http://<notifications_app_url>/swagger-ui/index.html
 */
@RestController
@RequestMapping("/emailNotifications")
public class EmailNotificationController implements EmailNotificationControllerSwagger {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotificationController.class);
    private static final String RATE_LIMITER_FALLBACK_MESSAGE = "Rate Limiter has been triggered for Notifications " +
            "Microservice´s EmailNotificationController, executing rateLimiterFallback() instead " +
            "sendNewOrderNotification().";

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public EmailNotificationController(final EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping("/send-new-order-notification")
    @RateLimiter(name = "notifications_microservice_rate_limiter", fallbackMethod = "rateLimiterFallback")
    public void sendNewOrderNotification(@RequestParam(value = "customerEmail") String customerEmail,
            @RequestParam(value = "orderId") Long orderId) {
        LOGGER.info("starts to execute emailNotificationController.sendNewOrderNotification() for customer email:{}" ,
            customerEmail);
        emailNotificationService.sendNewOrderNotification(customerEmail, orderId);
    }

    public void rateLimiterFallback(String customerEmail, Long orderId, Throwable ex) {
        LOGGER.info(RATE_LIMITER_FALLBACK_MESSAGE);
    }
}