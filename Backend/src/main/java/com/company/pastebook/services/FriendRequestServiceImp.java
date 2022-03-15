package com.company.pastebook.services;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.FriendRequestRepository;
import com.company.pastebook.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        if(friendRequestToBeAccepted.getStatus().equals("pending")) {
            if (friendRequestToBeAccepted != null) {
                friendRequestToBeAccepted.setStatus("accepted");
                LocalDate present = LocalDate.now();
                String timeStampDate = present.toString();
                friendRequestToBeAccepted.setRequestTimestamp(timeStampDate);
                friendRequestRepository.save(friendRequestToBeAccepted);



                return new ResponseEntity("Friend request accepted.", HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity("Friend request not found.", HttpStatus.NOT_ACCEPTABLE);
            }
        } else if(friendRequestToBeAccepted.getStatus().equals("rejected")){
            return new ResponseEntity("Rejected friend request.", HttpStatus.BAD_REQUEST);
        } else{
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





}


