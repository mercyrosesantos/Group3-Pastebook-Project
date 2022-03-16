package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
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


    // Find by requestor Id
    public Optional<FriendRequest> findByRequestorId(Long requestorId) {
        return Optional.ofNullable(friendRequestRepository.findByRequestorId(requestorId));
    }

    // Find by requestee Id
    public Optional<FriendRequest> findByRequesteeId(Long requesteeId) {
        return Optional.ofNullable(friendRequestRepository.findByRequesteeId(requesteeId));
    }

    // Find by status
    public Optional<FriendRequest> findByStatus(String status) {
        return Optional.ofNullable(friendRequestRepository.findByStatus(status));
    }


    //    Create friend request
    public void createFriendRequest(FriendRequest friendRequest, Long requesteeIdC) {
    }

    //    Accept friend request
    public ResponseEntity acceptFriendRequest(Long frid, String stringToken) {
        FriendRequest friendRequestToBeAccepted = friendRequestRepository.findById(frid).get();
        if (friendRequestToBeAccepted.getStatus().equals("pending")) {
            if (friendRequestToBeAccepted != null) {
                friendRequestToBeAccepted.setStatus("accepted");
                LocalDate present = LocalDate.now();
                String timeStampDate = present.toString();
                friendRequestToBeAccepted.setRequestTimestamp(timeStampDate);
                friendRequestRepository.save(friendRequestToBeAccepted);
                notificationService.createNotification("acceptedRequest", friendRequestToBeAccepted.getId());
                return new ResponseEntity("Friend request accepted.", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity("Friend request not found.", HttpStatus.NOT_ACCEPTABLE);
            }
        } else if(friendRequestToBeAccepted.getStatus().equals("rejected")){
            return new ResponseEntity("Rejected friend request.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity("Friend request already accepted.", HttpStatus.BAD_REQUEST);
        }
    }

     // Reject friend request
    public ResponseEntity rejectFriendRequest(Long frid, String stringToken) {
        FriendRequest friendRequestToBeRejected = friendRequestRepository.findById(frid).get();
        if(friendRequestToBeRejected.getStatus().equals("pending")) {
            if (friendRequestToBeRejected != null) {
                friendRequestToBeRejected.setStatus("rejected");
                LocalDate present = LocalDate.now();
                String timeStampDate = present.toString();
                friendRequestToBeRejected.setRequestTimestamp(timeStampDate);
                friendRequestRepository.save(friendRequestToBeRejected);
                return new ResponseEntity("Friend request rejected.", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity("Friend request not found.", HttpStatus.NOT_ACCEPTABLE);
            }
        } else if(friendRequestToBeRejected.getStatus().equals("accepted")){
            return new ResponseEntity("Friend request already accepted.", HttpStatus.BAD_REQUEST);
        } else{
            return new ResponseEntity("Friend request already rejected.", HttpStatus.BAD_REQUEST);
        }
    }

    // Get friend requests
    public Iterable<FriendRequest> getFriendRequests(String status){
        ArrayList<FriendRequest> friendRequestArrayList = new ArrayList<>();
        friendRequestArrayList.add(friendRequestRepository.findByStatus("pending"));
        return friendRequestArrayList;
    }


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
            System.out.println("requestor: " + friendRequest.getRequestor().getId());
            System.out.println("requeste: " + friendRequest.getRequestee().getId());
            System.out.println("status: " + friendRequest.getStatus());
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
    public ResponseEntity createOrUpdateFriendRequest(FriendRequest friendRequest) {
        System.out.println("friendRequest: " + friendRequest.getRequestee());
        friendRequestRepository.save(friendRequest);
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


            friendshipRepository.save(newFriendship1);
            friendshipRepository.save(newFriendship2);
            notificationService.createNotification("acceptedRequest", friendRequest.getId());
        } else {
            notificationService.createNotification("friendRequest", friendRequest.getId());
        }
        return new ResponseEntity("Friend request saved.", HttpStatus.OK);
    }



    public FriendRequest getFriendRequest(@PathVariable Long userId, @PathVariable Long friendId) {
        FriendRequest friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(userId,friendId);
        if (friendRequest == null ) {
            friendRequest = friendRequestRepository.findByRequestorIdAndRequesteeId(friendId,userId);
        }

        return friendRequest;
    }


}


