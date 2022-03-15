package com.company.pastebook.services;

import com.company.pastebook.models.Notification;
import com.company.pastebook.models.Post;
import com.company.pastebook.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
                Post getPost = new Post();
                getPost = reactionRepository.findById(eventId).get().getPost();
                newNotif.setUser(getPost.getTimelineUser());
                //newNotif.setUser(reactionRepository.findById(eventId).get().getPost().getUser());
                newNotif.setFriend(reactionRepository.findById(eventId).get().getUser());
                newNotif.setNotificationTimestamp(LocalDateTime.now());
                notificationRepository.save(newNotif);
                break;
            }
            case "commentedPost": {
                newNotif.setNotificationType("commented on your post.");
                newNotif.setUser(reactionRepository.findById(eventId).get().getPost().getTimelineUser());
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

}
