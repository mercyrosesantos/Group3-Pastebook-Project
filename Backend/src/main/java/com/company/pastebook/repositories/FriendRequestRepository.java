package com.company.pastebook.repositories;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Object> {
    FriendRequest findByRequestorId(Long requestorId);
    FriendRequest findByRequesteeId(Long requesteeId);
    <Optional> FriendRequest findByRequestorIdAndRequesteeId(Long requestorId, Long requesteeId);
    FriendRequest findByStatus(String status);
}
