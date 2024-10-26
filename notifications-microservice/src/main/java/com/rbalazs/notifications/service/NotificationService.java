package com.rbalazs.notifications.service;

import org.springframework.stereotype.Service;

/**
 * Notification Service.
 *
 * @author Rodrigo Balazs
 */
@Service
public class NotificationService {

    /**
     * Sends a notification with the message given as parameter.
     *
     * @param message the message to send.
     */
    public void sendNotification(String message) {
        //TODO(rodrigo.balazs) implement some notification method (email,etc)
    }
}
