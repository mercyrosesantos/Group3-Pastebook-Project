package com.company.pastebook.controllers;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.FriendRequestRepository;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.FriendRequestService;
import com.company.pastebook.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@CrossOrigin
public class FriendRequestController {
    @Autowired
    FriendRequestService friendRequestService;

    @Autowired
    UserRepository user;

    @Autowired
    FriendRequestRepository friendRequestRepo;

    @Autowired
    NotificationService notificationService;

//    @RequestMapping(value = "/api/search-result/{requestorIdC}/{requesteeIdC}", method = RequestMethod.POST)
//    public ResponseEntity<Object> createFriendRequest(FriendRequest friendRequest, @PathVariable Long requestorIdC, @PathVariable Long requesteeIdC) {
//        HashMap<String, String> response = new HashMap<>();
//        if (!user.existsById(requestorIdC) || !user.existsById(requesteeIdC)) {
//            response.put("result", "User/s does not exist");
//            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
//
//        } else {
//
//            if (requestorIdC == requesteeIdC) {
//                response.put("result", "Can't connect same users");
//                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
//            } else {
//                FriendRequest requestorR = friendRequestRepo.findByRequestorId(requestorIdC);
//                FriendRequest requesteeR = friendRequestRepo.findByRequesteeId(requesteeIdC);
//                if ((friendRequestRepo.findByRequestorId(requesteeIdC) != null && friendRequestRepo.findByRequesteeId(requestorIdC) != null) || (requestorR == requesteeR && (requestorR != null && requesteeR != null))) {
//                    response.put("result", "Request already exists.");
//                    return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
//                } else {
//                    User requestee = user.findById(requesteeIdC).get();
//                    FriendRequest friendRequest1 = new FriendRequest();
//                    friendRequest1.setRequestor(new User(requestorIdC));
//                    friendRequest1.setRequestee(requestee);
//                    LocalDate present = LocalDate.now();
//                    String timeStampDate = present.toString();
//                    friendRequest1.setRequestTimestamp(timeStampDate);
//                    response.put("result", "Add friend successful.");
//                    friendRequestRepo.save(friendRequest1);
//                    notificationService.createNotification("friendRequest", friendRequest1.getId());
//                    return new ResponseEntity<>(response, HttpStatus.CREATED);
//                }
//            }
//        }
//    }


    // Accept friend request
//    @RequestMapping(value = "/api/friendrequestsA/{frid}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> acceptFriendRequest(@PathVariable Long frid,@RequestHeader(value="Authorization") String stringToken){
//        return friendRequestService.acceptFriendRequest(frid, stringToken);
//
//    }

    // Reject friend request
//    @RequestMapping(value = "/api/friendrequestsR/{frid}", method = RequestMethod.PUT)
//    public ResponseEntity<Object> rejectFriendRequest(@PathVariable Long frid,@RequestHeader(value="Authorization") String stringToken){
//        return friendRequestService.rejectFriendRequest(frid, stringToken);
//    }

    // Get friend requests
    @RequestMapping(value = "/api/friendrequests", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendRequests(String status){
        String statusCheck = "pending";
        return new ResponseEntity<>(friendRequestService.findByStatus(statusCheck), HttpStatus.OK);
    }

    // Get friendship and if there's friend request sent
    @RequestMapping(value = "/api/friendship/{userId}/{friendId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendship(@PathVariable Long userId,@PathVariable Long friendId) {
        return new ResponseEntity<>(friendRequestService.getFriendship(userId, friendId), HttpStatus.OK);
    }

    //Create/Update friend request
    @RequestMapping(value = "/api/friendrequests/", method = RequestMethod.POST)
    public ResponseEntity<Object> friendship(@RequestBody FriendRequest friendRequest){
        return friendRequestService.createOrUpdateFriendRequest(friendRequest);
    }

    //Get friend request status
    @RequestMapping(value = "/api/friendrequests/{userId}/{friendId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendRequest(@PathVariable Long userId, @PathVariable Long friendId) {
        return new ResponseEntity<>(friendRequestService.getFriendRequest(userId,friendId), HttpStatus.OK);
    }

    // FriendsList
    @RequestMapping(value = "/api/friendslist/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriends(User friend, Long userId){
        return new ResponseEntity<>(friendRequestService.getFriends(friend,userId), HttpStatus.OK);
    }


    }
