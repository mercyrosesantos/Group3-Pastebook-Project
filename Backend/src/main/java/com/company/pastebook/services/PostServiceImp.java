package com.company.pastebook.services;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    //Get User Profile
    public Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId) {
        return postRepository.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId);
    }
}
