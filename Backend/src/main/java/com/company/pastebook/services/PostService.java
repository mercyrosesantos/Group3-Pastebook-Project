package com.company.pastebook.services;

import com.company.pastebook.models.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    //Get User Profile
    Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId,Long pageNo);
    // Create Post
    ResponseEntity createPost(Post post);
    // Get user posts and friends posts
    ResponseEntity getFeed(Long userId);
    // Get post by id
    ResponseEntity getPost(Long postId);

}
