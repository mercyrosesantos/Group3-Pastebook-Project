package com.company.pastebook.services;

import com.company.pastebook.models.Post;
import com.company.pastebook.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    //Get User Profile
    public Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId,Long pageNo) {
        Pageable myPage = PageRequest.of(pageNo.intValue(),10);
        return postRepository.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId,myPage);
    }

    // Create Post
    public ResponseEntity createPost (Post post){
        postRepository.save(post);
        return new ResponseEntity("Post created.", HttpStatus.OK);
    }
}
