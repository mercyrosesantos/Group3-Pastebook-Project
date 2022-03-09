package com.company.pastebook.services;

import com.company.pastebook.models.Post;

public interface PostService {

    //Get User Profile
    Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId);
}
