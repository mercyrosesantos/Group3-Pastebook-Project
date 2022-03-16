package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface FriendRequestService {

    // Create friend request
//    void createFriendRequest (FriendRequest friendRequest, Long requesteeIdC );

    // Accept friend request
//    ResponseEntity acceptFriendRequest(Long frid, String stringToken);

    // Find by requestor id
    Optional<FriendRequest> findByRequestorId(Long requestorId);

    // Find by requestee id
    Optional<FriendRequest> findByRequesteeId(Long requesteeId);

    // Find by status
    Optional<FriendRequest> findByStatus(String status);

    // Reject friend request
//    ResponseEntity rejectFriendRequest(Long frid, String stringToken);

    // Get pending friend requests
    Iterable<FriendRequest> getFriendRequests(String status);

    // Get friendship and if there's friend request sent
    Friendship getFriendship(Long requestorId, Long requesteeId);

    //Create/Update FriendRequest
    ResponseEntity createOrUpdateFriendRequest(FriendRequest friendRequest);

    //Get Friend Request status
    FriendRequest getFriendRequest(@PathVariable Long userId, @PathVariable Long friendId);

    // Friendslist
    ResponseEntity getFriends(User friend, Long userId);
}
