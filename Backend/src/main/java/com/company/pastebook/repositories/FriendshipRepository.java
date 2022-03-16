package com.company.pastebook.repositories;

import com.company.pastebook.models.Friendship;
import com.company.pastebook.models.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends CrudRepository<Friendship, Object> {

    Friendship findByUserIdAndFriendId(Long userId, Long friendId);

}
