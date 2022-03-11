package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface FriendRequestService {

    // Create friend request
    void createFriendRequest (FriendRequest friendRequest, Long requestorIdC, Long requesteeIdC );

    // Accept friend request
    ResponseEntity acceptFriendRequest(Long frid);

    // Find by requestor id
    Optional<FriendRequest> findByRequestorId(Long requestorId);

    // Find by requestee
//    Optional<FriendRequest> findByRequestee(User requesteeF);

    // Find by requestee id
    Optional<FriendRequest> friendrequestByRequesteeId(Long requesteeId);

}
