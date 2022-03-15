package com.company.pastebook.controllers;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.FriendRequestRepository;
import com.company.pastebook.repositories.UserRepository;
import com.company.pastebook.services.FriendRequestService;
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

    @RequestMapping(value = "/api/friendrequests/{requesteeIdC}", method = RequestMethod.POST)
    public ResponseEntity<Object> createFriendRequest(FriendRequest friendRequest,  @PathVariable Long requesteeIdC) {
        HashMap<String, String> response = new HashMap<>();

        FriendRequest requesteeR = friendRequestRepo.findByRequesteeId(requesteeIdC);
        User requestee = user.findById(requesteeIdC).get();
        FriendRequest friendRequest1 = new FriendRequest();
        friendRequest1.setRequestee(requestee);
        LocalDate present = LocalDate.now();
        String timeStampDate = present.toString();
        friendRequest1.setRequestTimestamp(timeStampDate);
        response.put("result", "Add friend successful.");
        friendRequestRepo.save(friendRequest1);
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }

    // Accept friend request
    @RequestMapping(value = "/api/friendrequestsA/{frid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> acceptFriendRequest(@PathVariable Long frid,@RequestHeader(value="Authorization") String stringToken){
        return friendRequestService.acceptFriendRequest(frid, stringToken);

    }

    // Reject friend request
    @RequestMapping(value = "/api/friendrequestsR/{frid}", method = RequestMethod.PUT)
    public ResponseEntity<Object> rejectFriendRequest(@PathVariable Long frid,@RequestHeader(value="Authorization") String stringToken){
        return friendRequestService.rejectFriendRequest(frid, stringToken);
    }

    // Get friend requests
    @RequestMapping(value = "/api/friendrequests", method = RequestMethod.GET)
    public ResponseEntity<Object> getFriendRequests(String status) {
        String statusCheck = "pending";
        return new ResponseEntity<>(friendRequestService.findByStatus(statusCheck), HttpStatus.OK);
    }

}
