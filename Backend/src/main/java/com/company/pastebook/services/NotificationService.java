package com.company.pastebook.services;

import org.springframework.http.ResponseEntity;

public interface NotificationService {

    // Create Notification
    ResponseEntity createNotification(String notifType, Long eventId);
}
