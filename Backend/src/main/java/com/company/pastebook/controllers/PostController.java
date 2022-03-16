package com.company.pastebook.controllers;

import com.company.pastebook.models.Post;
import com.company.pastebook.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class PostController {

    @Autowired
    PostService postService;

    //Get User Profile
    @RequestMapping(value = "/api/timeline/{timelineUserId}/{pageNo}", method = RequestMethod.GET)
    ResponseEntity getUserTimeline (@PathVariable Long timelineUserId, @PathVariable Long pageNo){
        return new ResponseEntity <> (postService.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId,pageNo),HttpStatus.OK);
    }

    // Create Post
    @RequestMapping(value = "api/posts/create", method = RequestMethod.POST)
    ResponseEntity<Object> createPost (@RequestBody Post post) {
        return new ResponseEntity<>(postService.createPost(post), HttpStatus.CREATED);
    }

    // Get posts for newsfeed
    @RequestMapping(value="/api/feed/{userId}", method = RequestMethod.GET)
    ResponseEntity<Object> getFeed (@PathVariable Long userId) {
        return postService.getFeed(userId);
    }

    // Get post by id
    @RequestMapping(value = "/api/posts/{postId}", method = RequestMethod.GET)
    ResponseEntity<Object> getPost (@PathVariable Long postId) {
        return postService.getPost(postId);
    }
}
