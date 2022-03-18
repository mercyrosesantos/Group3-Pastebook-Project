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
    @Autowired
    NotificationService notificationService;

    //Create Reaction
    public ResponseEntity createReaction(Reaction reaction) {
        reaction.setReactionTimestamp(new Date());
        reactionRepository.save(reaction);
        if (reaction.getReactionType().getId()==1){
            notificationService.createNotification("likedPost", reaction.getId());
        } else {
            notificationService.createNotification("commentedPost", reaction.getId());
        }
        return new ResponseEntity<>("Reaction Recorded",HttpStatus.CREATED);
    }

    //Get Comments by Post
    public ResponseEntity getCommentsByPost(Long postId) {
        return new ResponseEntity(reactionRepository.findByReactionTypeIdAndPostId(2l, postId),HttpStatus.OK);
    }

    //Get Likes by Post
    public ResponseEntity getLikesByPost(Long postId) {
        return new ResponseEntity(reactionRepository.findLikedUsers(postId), HttpStatus.OK);
    }
}
