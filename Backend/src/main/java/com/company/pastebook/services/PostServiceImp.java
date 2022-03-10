package com.company.pastebook.services;

import com.company.pastebook.models.Post;
import com.company.pastebook.models.User;
import com.company.pastebook.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.util.calendar.BaseCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PostServiceImp implements PostService{

    @Autowired
    private PostRepository postRepository;

    //Get User Profile
    public Iterable<Post> findByTimelineUserIdOrderByPostTimestampDesc(Long timelineUserId) {
        return postRepository.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId);
    }

    // Create Post
    public ResponseEntity createPost (Post post){
        postRepository.save(post);
        return new ResponseEntity("Post created.", HttpStatus.OK);
    }
}
