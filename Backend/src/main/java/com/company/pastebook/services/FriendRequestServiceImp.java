package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.FriendRequestRepository;
import com.company.pastebook.repositories.FriendshipRepository;
import com.company.pastebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


@Service
public class FriendRequestServiceImp implements FriendRequestService{

    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FriendshipRepository friendshipRepository;


    // Find by status
    public Optional<FriendRequest> findByStatus(String status) {
        return Optional.ofNullable(friendRequestRepository.findByStatus(status));
    }


    // Get friendship and if there's friend request sent
    public Friendship getFriendship(Long requestorId, Long requesteeId) {
        Friendship friendship = friendshipRepository.findByUserIdAndFriendId(requestorId, requesteeId);
        if (friendship == null) {
            friendship = new Friendship();
            friendship.setStatus("strangers");
            FriendRequest friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(requestorId,requesteeId);
            if (friendRequest == null ) {
                friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(requesteeId,requestorId);
            }

            if (friendRequest == null) {
                return friendship;
            }
            if (friendRequest.getStatus().equalsIgnoreCase("rejected")) {
                friendship.setStatus("rejected");
            } else if (friendRequest.getStatus().equalsIgnoreCase("pending")) {
                if (friendRequest.getRequestor().getId().equals(requestorId)) {
                    friendship.setStatus("awaiting confirmation");
                } else if (friendRequest.getRequestee().getId().equals(requestorId)) {
                    friendship.setStatus("pending");
                }
            }
        } else {
            friendship.setStatus("friends");
        }
        return friendship;
    }

    //Create/Update FriendRequest
    public ResponseEntity createOrUpdateFriendRequest(FriendRequest friendRequest) {
        Boolean isNew = friendRequest.getId() == null;
        FriendRequest savedFriendRequest = friendRequestRepository.save(friendRequest);
        if (isNew) {
            notificationService.createNotification("friendRequest", savedFriendRequest.getId());
        }
        if (friendRequest.getStatus().equalsIgnoreCase("accepted")) {
            Friendship newFriendship1 = new Friendship();
            Friendship newFriendship2 = new Friendship();
            newFriendship1.setUser(new User(friendRequest.getRequestor().getId()));
            newFriendship1.setFriend(new User(friendRequest.getRequestee().getId()));
            newFriendship1.setFriendshipTimestamp(new Date().toString());
            newFriendship1.setActive(true);

            newFriendship2.setUser(new User(friendRequest.getRequestee().getId()));
            newFriendship2.setFriend(new User(friendRequest.getRequestor().getId()));
            newFriendship2.setFriendshipTimestamp(new Date().toString());
            newFriendship1.setActive(true);
            notificationService.createNotification("acceptedRequest", friendRequest.getId());

            friendshipRepository.save(newFriendship1);
            friendshipRepository.save(newFriendship2);
            notificationService.createNotification("acceptedRequest", friendRequest.getId());
        } else {
            notificationService.createNotification("friendRequest", friendRequest.getId());
        }
        return new ResponseEntity("Friend request saved.", HttpStatus.OK);
    }


    //Get Friend Request status
    public FriendRequest getFriendRequest(@PathVariable Long userId, @PathVariable Long friendId) {
        FriendRequest friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(userId,friendId);
        if (friendRequest == null ) {
            friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(friendId,userId);
        }
        return friendRequest;
    }

    //Friends List
    public ResponseEntity getFriends(Long userId) {
        ArrayList<User> friends = new ArrayList<>();
        for (Friendship friendships : friendshipRepository.findAll()) {
            if (friendships.getUser().getId().equals(userId)) {
                friends.add(friendships.getFriend());
            }
        }
        return new ResponseEntity(friends, HttpStatus.OK);
    }
}


