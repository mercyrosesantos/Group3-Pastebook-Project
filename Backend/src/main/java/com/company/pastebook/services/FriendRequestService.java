package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface FriendRequestService {

    // Create friend request
    void createFriendRequest (FriendRequest friendRequest, Long requesteeIdC );

    // Accept friend request
    ResponseEntity acceptFriendRequest(Long frid, String stringToken);

    // Find by requestor id
    Optional<FriendRequest> findByRequestorId(Long requestorId);

    // Find by requestee id
    Optional<FriendRequest> findByRequesteeId(Long requesteeId);

    // Find by status
    Optional<FriendRequest> findByStatus(String status);

    // Reject friend request
    ResponseEntity rejectFriendRequest(Long frid, String stringToken);

    // Get pending friend requests
    Iterable<FriendRequest> getFriendRequests(String status);

}
