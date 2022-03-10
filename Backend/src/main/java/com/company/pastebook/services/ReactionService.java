package com.company.pastebook.services;

import com.company.pastebook.models.Reaction;
import org.springframework.http.ResponseEntity;

public interface ReactionService {

    //Create Reaction
    ResponseEntity createReaction(Reaction reaction);

    //Get Comments by Post
    public ResponseEntity getCommentsByPost(Long postId);

    //Get Likes by Post
    public ResponseEntity getLikesByPost(Long postId);

}
