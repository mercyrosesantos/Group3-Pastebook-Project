package com.company.pastebook.repositories;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Object> {
    Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId);

}
