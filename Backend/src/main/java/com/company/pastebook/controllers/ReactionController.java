package com.company.pastebook.controllers;

import com.company.pastebook.models.Reaction;
import com.company.pastebook.services.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReactionController {

    @Autowired
    ReactionService reactionService;

    //Create Reaction
    @RequestMapping(value="/api/reactions", method= RequestMethod.POST)
    public ResponseEntity<Object> createReaction(@RequestBody Reaction reaction){
        return reactionService.createReaction(reaction);
    }

    //Get Comments by Post
    @RequestMapping(value = "/api/comments/{postId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getCommentsByPost(@PathVariable long postId) {
        return reactionService.getCommentsByPost(postId);
    }
    //Get Likes by Post
    @RequestMapping(value = "/api/likes/{postId}", method=RequestMethod.GET)
    public ResponseEntity<Object> getLikesByPost(@PathVariable long postId) {
        return reactionService.getLikesByPost(postId);
    }
}
