package com.company.pastebook.repositories;

import com.company.pastebook.models.Friendship;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Object> {
}
