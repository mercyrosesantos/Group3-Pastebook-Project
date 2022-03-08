package com.company.pastebook.controllers;

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

    @RequestMapping(value = "/api/profile/{timelineUserId}", method = RequestMethod.GET)
    ResponseEntity getUserTimeline (@PathVariable Long timelineUserId){
        return new ResponseEntity <> (postService.findByTimelineUserIdOrderByPostTimestampDesc(timelineUserId),HttpStatus.OK);
    }
}
