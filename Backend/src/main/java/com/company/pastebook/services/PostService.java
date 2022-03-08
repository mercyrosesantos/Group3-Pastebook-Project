package com.company.pastebook.services;

import com.company.pastebook.models.Post;

public interface PostService {
    Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId);
}
