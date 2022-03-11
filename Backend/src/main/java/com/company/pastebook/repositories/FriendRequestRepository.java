package com.company.pastebook.repositories;

import com.company.pastebook.models.FriendRequest;
import com.company.pastebook.models.User;
import org.springframework.data.repository.CrudRepository;

public interface FriendRequestRepository extends CrudRepository<FriendRequest, Object> {
    FriendRequest findByRequestorId(Long requestorId);
    FriendRequest findByRequesteeId(Long requesteeId);
//    FriendRequest findByRequestee(User requesteeF);
}
