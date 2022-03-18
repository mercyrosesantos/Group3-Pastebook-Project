package com.company.pastebook.services;

import com.company.pastebook.models.Reaction;
import org.springframework.http.ResponseEntity;

public interface ReactionService {

    //Create Reaction
    ResponseEntity createReaction(Reaction reaction);

    //Get Comments by Post
    ResponseEntity getCommentsByPost(Long postId);

    //Get Likes by Post
    ResponseEntity getLikesByPost(Long postId);

}
