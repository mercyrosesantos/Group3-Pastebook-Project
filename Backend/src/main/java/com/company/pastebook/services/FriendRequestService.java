package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface FriendRequestService {

    // Find by status
    Optional<FriendRequest> findByStatus(String status);

    // Get friendship and if there's friend request sent
    Friendship getFriendship(Long requestorId, Long requesteeId);

    //Create/Update FriendRequest
    ResponseEntity createOrUpdateFriendRequest(FriendRequest friendRequest);

    //Get Friend Request status
    FriendRequest getFriendRequest(@PathVariable Long userId, @PathVariable Long friendId);

    // Friendslist
    ResponseEntity getFriends(Long userId);
}
