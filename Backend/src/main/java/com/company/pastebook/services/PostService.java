package com.company.pastebook.services;

import com.company.pastebook.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //Get User Profile
    Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId);
    // Create Post
    ResponseEntity createPost(Post post);
}
