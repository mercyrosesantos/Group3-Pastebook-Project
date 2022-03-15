package com.company.pastebook.controllers;

import com.company.pastebook.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    // Get user notifications
    @RequestMapping(value = "/api/notifications/{userId}", method = RequestMethod.GET)
    ResponseEntity<Object> getUserNotif(@PathVariable Long userId){
        return new ResponseEntity<>(notificationService.getUserNotif(userId), HttpStatus.OK);
    }

    // Get unread notifications count
    @RequestMapping(value = "/api/notifications/unread/{userId}", method = RequestMethod.GET)
    ResponseEntity<Object> getUnreadCount(@PathVariable Long userId) {
        return notificationService.getUnreadCount(userId);
    }

    // Set as notifications as read
    @RequestMapping(value = "/api/notifications/read/{userId}", method = RequestMethod.GET)
    ResponseEntity<Object> setAsRead(@PathVariable Long userId) {
        return notificationService.setAsRead(userId);
    }
}
