package com.company.pastebook.services;

import com.company.pastebook.models.Reaction;
import com.company.pastebook.repositories.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReactionServiceImp implements ReactionService {

    @Autowired
    ReactionRepository reactionRepository;

    //Create Reaction
    public ResponseEntity createReaction(Reaction reaction) {
        reaction.setReactionTimestamp(new Date());
        System.out.println("reactionType: " + reaction.getReactionType());
        System.out.println("post: " + reaction.getPost());
        System.out.println("user: " + reaction.getUser());
        reactionRepository.save(reaction);
        return new ResponseEntity<>("Reaction Recorded",HttpStatus.CREATED);
    }

    //Get Comments by Post
    public ResponseEntity getCommentsByPost(Long postId) {
        return new ResponseEntity(reactionRepository.findByReactionTypeIdAndPostId(2l, postId),HttpStatus.OK);
    }
}
