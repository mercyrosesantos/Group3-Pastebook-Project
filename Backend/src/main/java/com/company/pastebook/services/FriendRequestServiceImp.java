package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.FriendRequestRepository;
import com.company.pastebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class FriendRequestServiceImp implements FriendRequestService{

    @Autowired
    private FriendRequestRepository friendRequestRepository;
    @Autowired
    private UserRepository userRepository;


    // Find by requestor Id
    public Optional<FriendRequest> findByRequestorId(Long requestorId) {
        return Optional.ofNullable(friendRequestRepository.findByRequestorId(requestorId));
    }

    // Find by requestee Id
    public Optional<FriendRequest> friendrequestByRequesteeId(Long requesteeId) {
        return Optional.ofNullable(friendRequestRepository.findByRequesteeId(requesteeId));
    }

    // Find by requestee
//    public Optional<FriendRequest> findByRequestee(User requesteeF){
//        return Optional.ofNullable(friendRequestRepository.findByRequestee(requesteeF));
//    }

    //    Create friend request
    public void createFriendRequest(FriendRequest friendRequest, Long requestorIdC, Long requesteeIdC) {
    }

    //    Accept friend request
    public ResponseEntity acceptFriendRequest(Long frid) {
        FriendRequest friendRequestToBeAccepted = friendRequestRepository.findById(frid).get();

        if (friendRequestToBeAccepted != null){
            friendRequestToBeAccepted.setStatus("accepted");
            friendRequestRepository.save(friendRequestToBeAccepted);
            return new ResponseEntity("Friend request accepted.", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity("Friend request not found.", HttpStatus.NOT_ACCEPTABLE);
        }

    }


}


