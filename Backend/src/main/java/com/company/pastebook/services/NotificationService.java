package com.company.pastebook.services;

import com.company.pastebook.models.Notification;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface NotificationService {

    // Create Notification
    ResponseEntity createNotification(String notifType, Long eventId);

    // Get user notifications
    ArrayList<Notification> getUserNotif(Long userId);

    // Get unread notifications count
    ResponseEntity getUnreadCount(Long userId);

    // Set as read
    ResponseEntity setAsRead(Long userId);
}
