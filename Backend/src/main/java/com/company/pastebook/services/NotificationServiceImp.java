package com.company.pastebook.services;

import com.company.pastebook.models.Notification;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class NotificationServiceImp implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    ReactionRepository reactionRepository;
    @Autowired
    FriendRequestRepository friendRequestRepository;
    @Autowired
    UserRepository userRepository;

    // try method
    @Autowired
    PostService postService;

    // Create notification
    public ResponseEntity<Object> createNotification(String notifType, Long eventId){
        Notification newNotif = new Notification();
        switch (notifType) {
            case "friendRequest": {
                newNotif.setNotificationType("sent you a friend request.");
                newNotif.setUser(friendRequestRepository.findById(eventId).get().getRequestee());
                newNotif.setFriend(userRepository.findById(friendRequestRepository.findById(eventId).get().getRequestorId()).get());
                newNotif.setNotificationTimestamp(LocalDateTime.now());
                notificationRepository.save(newNotif);
                break;
            }
            case "acceptedRequest": {
                newNotif.setNotificationType("accepted your friend request.");
                newNotif.setUser(userRepository.findById(friendRequestRepository.findById(eventId).get().getRequestorId()).get());
                newNotif.setFriend(friendRequestRepository.findById(eventId).get().getRequestee());
                newNotif.setNotificationTimestamp(LocalDateTime.now());
                notificationRepository.save(newNotif);
                break;
            }
            case "likedPost": {
                newNotif.setNotificationType("liked your post.");
//                newNotif.setUser(reactionRepository.findById(eventId).get().getPost().getUser());
                for (User indivUser: userRepository.findAll()){
                    if (indivUser.getUserPosts().contains(reactionRepository.findById(eventId).get().getPost())){
                        newNotif.setUser(indivUser);
                    }
                }
                newNotif.setPost(reactionRepository.findById(eventId).get().getPost());
                newNotif.setFriend(reactionRepository.findById(eventId).get().getUser());
                newNotif.setNotificationTimestamp(LocalDateTime.now());
                notificationRepository.save(newNotif);
                break;
            }
            case "commentedPost": {
                newNotif.setNotificationType("commented on your post.");
//                newNotif.setUser(reactionRepository.findById(eventId).get().getPost().getUser());
                for (User indivUser: userRepository.findAll()){
                    if (indivUser.getUserPosts().contains(reactionRepository.findById(eventId).get().getPost())){
                        newNotif.setUser(indivUser);
                    }
                }
                newNotif.setPost(reactionRepository.findById(eventId).get().getPost());
                newNotif.setFriend(reactionRepository.findById(eventId).get().getUser());
                newNotif.setNotificationTimestamp(LocalDateTime.now());
                notificationRepository.save(newNotif);
                break;
            } default: {
                return new ResponseEntity<>("Invalid notification type.", HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(newNotif, HttpStatus.CREATED);
    }

    // Get user notifications
    public ArrayList<Notification> getUserNotif(Long userId) {
        ArrayList<Notification> userNotif = new ArrayList<>();
        User user = userRepository.findById(userId).get();
        for (Notification notif: notificationRepository.findAll()){
            if (notif.getUser()!=null){
                if (notif.getUser().getId().equals(userId)){
                    userNotif.add(notif);
                }
            } else if (notif.getPost()!=null) {
                if (notif.getPost().getUser().equals(user)) {
                    userNotif.add(notif);
                }
            }
        }
        Collections.sort(userNotif, Collections.reverseOrder());
        return userNotif;
    }

    // Get unread notifications count
    public int getUnreadCount(Long userId) {
        ArrayList<Notification> userNotif = getUserNotif(userId);
        int count = 0;
        for (Notification notification: userNotif) {
            if(!notification.isRead()){
                count++;
            }
        }
        return count;
    }

    // Set notifications as read
    public ResponseEntity<Object> setAsRead(Long userId) {
        ArrayList<Notification> userNotif = getUserNotif(userId);
        for (Notification notification: userNotif) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }
        return new ResponseEntity<>(getUnreadCount(userId), HttpStatus.OK);
    }

}
